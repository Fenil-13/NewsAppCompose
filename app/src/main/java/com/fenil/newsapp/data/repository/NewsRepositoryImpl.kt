package com.fenil.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fenil.newsapp.data.local.NewsDao
import com.fenil.newsapp.data.models.NewsArticle
import com.fenil.newsapp.data.remote.NewsApi
import com.fenil.newsapp.data.remote.NewsPagingSource
import com.fenil.newsapp.data.remote.SearchNewsPagingSource
import com.fenil.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl (
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
): NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<NewsArticle>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(newsApi = newsApi, sources = sources.joinToString(separator = ","))
            }
        ).flow
    }

    override fun searchNews(
        searchQuery: String,
        sources: List<String>
    ): Flow<PagingData<NewsArticle>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    api = newsApi,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: NewsArticle) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: NewsArticle) {
        newsDao.delete(article)
    }

    override fun getArticles(): Flow<List<NewsArticle>> {
        return newsDao.getArticles()
    }

    override suspend fun getArticle(url: String): NewsArticle? {
        return newsDao.getArticle(url = url)
    }
}