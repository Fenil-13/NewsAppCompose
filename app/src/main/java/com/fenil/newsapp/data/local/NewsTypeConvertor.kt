package com.fenil.newsapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.fenil.newsapp.data.models.NewsArticleSource

@ProvidedTypeConverter
class NewsTypeConvertor {

    @TypeConverter
    fun sourceToString(source: NewsArticleSource): String{
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): NewsArticleSource{
        return source.split(',').let { sourceArray ->
            NewsArticleSource(id = sourceArray[0], name = sourceArray[1])
        }
    }
}