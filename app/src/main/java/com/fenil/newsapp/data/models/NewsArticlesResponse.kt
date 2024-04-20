package com.fenil.newsapp.data.models

data class NewsArticlesResponse(
    val articles: List<NewsArticle>,
    val status: String,
    val totalResults: Int
)