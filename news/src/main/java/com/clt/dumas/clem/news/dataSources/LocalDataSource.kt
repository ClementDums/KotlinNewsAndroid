package com.clt.dumas.clem.news.dataSources

import com.clt.dumas.clem.news.dataSources.dao.ArticleDao
import com.clt.dumas.clem.news.dataSources.database.NewsDatabase
import com.clt.dumas.clem.news.entities.Article

class LocalDataSource{
    private var db: NewsDatabase? = null
    private var articleDao: ArticleDao? = null
    init {
        db = NewsDatabase.getNewsDatabase()
        articleDao = db?.articleDao()

    }
    fun updateArticle(article:Article){
        articleDao?.updateArticle(article)
   }
    fun getLocalArticles():List<Article>{
        return articleDao?.getArticles() ?: emptyList()
    }

    fun getFavoriteArticles():List<Article>{
        return articleDao?.getFavoriteArticles() ?: emptyList()
    }
    fun insertArticles(articles: List<Article>){
        articleDao?.insertArticles(articles)
    }
    fun deleteArticles(){
        articleDao?.deleteArticles()
    }
}