package com.fenil.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fenil.newsapp.data.models.NewsArticle
import com.fenil.newsapp.utils.Constants

class SearchNewsPagingSource(
    private val api: NewsApi,
    private val searchQuery: String,
    private val sources: String
) : PagingSource<Int, NewsArticle>() {

    override fun getRefreshKey(state: PagingState<Int, NewsArticle>): Int? {
        return state.anchorPosition?.let { anchorPage ->
            val page = state.closestPageToPosition(anchorPage)
            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsArticle> {
        val page = params.key ?: 1
        return try {
            val newsResponse = api.searchNews(searchQuery = searchQuery, sources = sources, page = page, apiKey = Constants.API_KEY)
            totalNewsCount += newsResponse.articles.size
            val NewsArticles = newsResponse.articles.distinctBy { it.title } //Remove duplicates

            LoadResult.Page(
                data = NewsArticles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }


}