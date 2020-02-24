package com.clt.dumas.clem.news.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.clt.dumas.clem.news.R
import com.clt.dumas.clem.news.entities.Article
import com.squareup.picasso.Picasso

class ArticleAdapter (val handler:(article:Article)->Unit): RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private val dataset: MutableList<Article> = mutableListOf()

    inner class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Article) {
            val txtTitle = root.findViewById<TextView>(R.id.titre)
            val txtDesc = root.findViewById<TextView>(R.id.description)
            val ulrImg = root.findViewById<ImageView>(R.id.image_article)
            val signet = root.findViewById<ImageView>(R.id.signet)
            if(item.isLiked){
                signet.visibility = View.VISIBLE
            }else{
                signet.visibility = View.INVISIBLE
            }
            txtTitle.text = item.title
            txtDesc.text = item.description
            Picasso.get().load(item.urlToImage).into(ulrImg)

            root.setOnClickListener {
                handler(item)
            }
        }
    }

    fun updateData(list: List<Article>) {
        dataset.clear()
        dataset.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.articles_list_fragment_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size

}