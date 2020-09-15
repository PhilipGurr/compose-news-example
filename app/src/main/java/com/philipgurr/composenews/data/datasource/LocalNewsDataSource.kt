package com.philipgurr.composenews.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.philipgurr.composenews.domain.NewsPost
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalNewsDataSource {
    @Query("SELECT * from newspost")
    fun getAll(): Flow<List<NewsPost>>

    @Insert
    suspend fun insert(newsPost: NewsPost)

    @Delete
    suspend fun delete(newsPost: NewsPost)
}