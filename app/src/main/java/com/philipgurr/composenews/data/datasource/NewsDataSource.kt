package com.philipgurr.composenews.data.datasource

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsDataSource {
    @GET("top-headlines?apiKey=4b3028aa63be4435b22a827d60aab3ed")
    suspend fun getNewsPosts(@Query("country") countryCode: String): NewsPostsResponse
}