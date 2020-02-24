package com.clt.dumas.clem.news.viewModels

import android.content.Context
import android.content.Intent
import androidx.lifecycle.*
import com.clt.dumas.clem.news.entities.Article
import com.clt.dumas.clem.news.repositories.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticlesViewModel:ViewModel(){
    private val repository: ArticleRepository = ArticleRepository()
    var isConnected = false
    private val _listArticles = MutableLiveData<List<Article>>()
    val listArticles: LiveData<List<Article>>
        get() = _listArticles


    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getArticles(isConnected)
            _listArticles.postValue(result)
        }
    }

    fun loadFavoritesArticles(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getFavoriteArticles()
            _listArticles.postValue(result)
        }
    }

    fun updateData(article: Article){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateArticle(article)
        }
    }

    fun toggleLike(article: Article){
        article.isLiked = !article.isLiked
        updateData(article)

    }

    fun share(context:Context, article: Article){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, article.url)
            type = "text/plain"

        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

}