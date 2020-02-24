package com.clt.dumas.clem.news.dataSources

import com.clt.dumas.clem.news.entities.Article
import com.clt.dumas.clem.news.services.ArticleService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RemoteDataSource{
    private val service: ArticleService
    private val apiKey = "f3050b4a04cb4cbab758e5db0af52bac"
    private val subjet = "art"
    private val language = "fr"


    init {

        val retrofit = Retrofit.Builder()
            .apply {
            //Ajouter un converter pour JSON
            //Ici on utilise gson.
            addConverterFactory(GsonConverterFactory.create())
            //Ajouter l'url de base du web service
            baseUrl("https://newsapi.org/v2/")
        }.build()


        //Créer une instance du service
        service = retrofit.create(ArticleService::class.java)
    }

    fun getRemoteArticles(): List<Article> {
        val result = service.getArticles(apiKey, subjet, language).execute()

        return if(result.isSuccessful) {
            result.body()?.articles ?: emptyList()
        }else {
            emptyList()
        }
    }
}