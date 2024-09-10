package com.example.tasks.tasks2.task21.network

import com.example.tasks.tasks2.task21.model.NewsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "ae1ab8534fa94376a1f298e246d402e2"

interface NewsAPI {

    @GET("v2/top-headlines")
    fun getTopNews(
        @Query("country")
        countryCode: String = "us",
        @Query("category")
        category: String = "general",
        @Query("page")
        pageNumber: Int = 1,
        @Query("pageSize")
        pageSize: Int = 20,
        @Query("apiKey")
        apiKey: String = API_KEY,
    ): Single<NewsResponse>
}

