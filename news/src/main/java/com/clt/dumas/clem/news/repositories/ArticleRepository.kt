package com.clt.dumas.clem.news.repositories

import com.clt.dumas.clem.news.dataSources.LocalDataSource
import com.clt.dumas.clem.news.dataSources.RemoteDataSource
import com.clt.dumas.clem.news.entities.Article

class ArticleRepository {

    private val online = RemoteDataSource()
    private val offline = LocalDataSource()
    private lateinit var articles: MutableList<Article>

    fun getArticles(isConnected:Boolean):List<Article>  {

        val offlineArticles = offline.getLocalArticles()
        var onlineArticles = emptyList<Article>()
        if(isConnected){
            onlineArticles = online.getRemoteArticles()
        }
        articles = offlineArticles.toMutableList()
        val articlesTitle = articles.map { it.title }
        for (online in onlineArticles){
            if(online.title !in articlesTitle){
               if(online.urlToImage == null){
                       online.urlToImage = "https://www.omv.fr/uploads/files/image-t-site1route-8.jpg"
                   }
                articles.add(online)
            }
        }
        if (isConnected){ offline.insertArticles(articles)}
        return articles
    }

    fun getFavoriteArticles():List<Article>{
        val articles = offline.getFavoriteArticles()
        return articles
    }

    fun updateArticle(article: Article){
        offline.updateArticle(article)
    }
}