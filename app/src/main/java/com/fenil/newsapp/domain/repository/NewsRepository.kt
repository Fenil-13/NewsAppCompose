package com.fenil.newsapp.domain.repository

import androidx.paging.PagingData
import com.fenil.newsapp.data.models.NewsArticle
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<NewsArticle>>

    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<NewsArticle>>

    suspend fun upsertArticle(article: NewsArticle)

    suspend fun deleteArticle(article: NewsArticle)

    fun getArticles(): Flow<List<NewsArticle>>

    suspend fun getArticle(url: String): NewsArticle?

}