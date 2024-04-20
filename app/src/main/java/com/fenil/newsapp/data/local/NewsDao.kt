package com.fenil.newsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fenil.newsapp.data.models.NewsArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: NewsArticle)

    @Delete
    suspend fun delete(article: NewsArticle)

    @Query("SELECT * FROM NewsArticle")
    fun getArticles(): Flow<List<NewsArticle>>

    @Query("SELECT * FROM NewsArticle WHERE url=:url")
    suspend fun getArticle(url: String): NewsArticle?
}