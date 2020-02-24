package com.clt.dumas.clem.news.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.clt.dumas.clem.news.R
import com.clt.dumas.clem.news.entities.Article
import com.clt.dumas.clem.news.viewModels.ArticlesViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_detail_fragment.*

class ArticleDetailFragment : Fragment() {

    val myArticle: Article by lazy {
        arguments?.getParcelable(ARGS_ARTICLE) ?: Article(
            "ERROR",
            "Cet article n'existe pas",
            "",
            urlToImage = ""
        )
    }
    lateinit var viewModel: ArticlesViewModel


    companion object {
        const val ARGS_ARTICLE = "ARGS_ARTICLE"
        fun newInstance(myArticle: Article): ArticleDetailFragment {
            return ArticleDetailFragment().apply {
                //On sauvegarde l’opération dans les arguments,
                //Si le fragment se recrée, la valeur sera restaurée
                arguments = bundleOf(ARGS_ARTICLE to myArticle)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArticlesViewModel::class.java)
        viewModel.loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.article_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titre.text = myArticle.title
        description.text = myArticle.description
        author.text = myArticle.author
        Picasso.get().load(myArticle.urlToImage).into(image_article)
        full_article.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(myArticle.url)
            startActivity(i)
        }

        if (myArticle.isLiked) {
            like.setColorFilter(Color.argb(255, 255, 255, 255))
        }

        like.setOnClickListener {
            viewModel.toggleLike(myArticle)
            if (myArticle.isLiked) {
                like.setColorFilter(Color.argb(255, 255, 255, 255))
            } else {
                like.setColorFilter(Color.argb(255, 128, 128, 128))
            }
        }

        share.setOnClickListener {
            viewModel.share(activity!!, myArticle)
        }
    }
}