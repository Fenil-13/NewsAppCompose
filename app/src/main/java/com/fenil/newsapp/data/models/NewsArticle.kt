package com.fenil.newsapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsArticle(
    val author:String,
    val title: String,
    val description: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    val source: NewsArticleSource
)

data class NewsArticleSource(
    val id: String,
    val name: String
)