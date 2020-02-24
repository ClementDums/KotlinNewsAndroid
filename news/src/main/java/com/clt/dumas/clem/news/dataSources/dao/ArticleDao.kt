package com.clt.dumas.clem.news.dataSources.dao

import androidx.room.*
import com.clt.dumas.clem.news.entities.Article

@Dao
interface ArticleDao {

    @Query("SELECT * from article")
    fun getArticles(): List<Article>

    @Query("DELETE from article")
    fun deleteArticles()

    @Query("SELECT * from article WHERE isLiked = 1")
    fun getFavoriteArticles(): List<Article>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertArticles(articles: List<Article>)

    @Update
    fun updateArticle(article: Article)
}