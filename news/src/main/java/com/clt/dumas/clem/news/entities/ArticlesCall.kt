package com.clt.dumas.clem.news.entities

data class ArticlesCall(val status:String,val totalResults:Int, val articles:List<Article>)