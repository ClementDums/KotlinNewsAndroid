package com.clt.dumas.clem.news.services

import com.clt.dumas.clem.news.entities.ArticlesCall
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService{
    @GET("everything")
    fun getArticles(@Query("apiKey")apiKey:String,@Query("q")subject:String, @Query("language")language:String): Call<ArticlesCall>
}