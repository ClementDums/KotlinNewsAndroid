package com.clt.dumas.clem.news.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.clt.dumas.clem.news.R
import com.clt.dumas.clem.news.adapters.ArticleAdapter
import com.clt.dumas.clem.news.viewModels.ArticlesViewModel
import kotlinx.android.synthetic.main.articles_list_fragment.*

class FavoritesFragment:Fragment(){
    lateinit var viewModel: ArticlesViewModel
    private val adapterRecycler: ArticleAdapter = ArticleAdapter {
        val articleDetailFragment = ArticleDetailFragment.newInstance(it)
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.articles_fragment_container, articleDetailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArticlesViewModel::class.java)
        viewModel.loadFavoritesArticles()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.articles_list_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //définir l'orientation des élements (vertical)
        myrecycler.layoutManager = LinearLayoutManager(context)
        //associer l'adapter à la recyclerview
        myrecycler.adapter = adapterRecycler

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.listArticles.observe(viewLifecycleOwner, Observer {
            adapterRecycler.updateData(it)
        })
    }

}