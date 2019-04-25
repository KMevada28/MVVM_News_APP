package com.newsapp.network

import com.newsapp.response.NewsResponseEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi{

    /**
     * Get the list of the News from the API
     */

    @GET("top-headlines")
    fun getTopNewResponse(
        @Query("country") q: String,
        @Query("category") from: String
    ): Observable<NewsResponseEntity>

}