package com.philipgurr.composenews.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.philipgurr.composenews.domain.NewsPost

@Database(entities = [NewsPost::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun localNewsDao(): LocalNewsDataSource
}