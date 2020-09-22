package com.philipgurr.composenews.data.datasource

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsDataSource {
    @GET("top-headlines?apiKey=")
    suspend fun getNewsPosts(@Query("country") countryCode: String): NewsPostsResponse
}