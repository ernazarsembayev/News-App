package com.ernazar.newsapp.data.network.api

import androidx.annotation.IntRange
import com.ernazar.newsapp.data.network.model.articlesResponse.ArticlesResponseDto
import com.ernazar.newsapp.utils.Config
import com.ernazar.newsapp.utils.Config.DEFAULT_PAGE_SIZE
import com.ernazar.newsapp.utils.Config.MAX_PAGE_SIZE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {

    @GET("everything")
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("pageSize") @IntRange(from = 1, to = MAX_PAGE_SIZE.toLong()) pageSize: Int = DEFAULT_PAGE_SIZE,
    ): Response<ArticlesResponseDto?>

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("q") query: String,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("pageSize") @IntRange(from = 1, to = MAX_PAGE_SIZE.toLong()) pageSize: Int
    ): Response<ArticlesResponseDto?>

    companion object {

        private var BASE_URL = Config.BASE_URL + "v2/"

        fun create() : ArticleService {

            val retrofit = BaseApi.createRetrofit(BASE_URL)

            return retrofit.create(ArticleService::class.java)

        }
    }

}