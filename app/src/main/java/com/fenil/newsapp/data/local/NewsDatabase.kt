package com.fenil.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fenil.newsapp.data.models.NewsArticle

@Database(entities = [NewsArticle::class], version = 1)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDatabase: RoomDatabase(){
    abstract val newsDao: NewsDao
}
