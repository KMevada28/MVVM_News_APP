package com.newsapp.response

data class NewsResponseEntity(val status: String?,
                         val totalResults: Int,
                         val articles: List<ArticleResponseEntity>?) {

}