package com.ernazar.newsapp.data.server.api

import com.ernazar.newsapp.data.server.response.getArticlesResponse.GetArticlesResponse
import com.ernazar.newsapp.utils.Config
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {

    @GET("everything")
    suspend fun getEverything(@Query("q") query: String): Response<GetArticlesResponse?>

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("q") query: String): Response<GetArticlesResponse?>

    companion object {

        private var BASE_URL = Config.BASE_URL + "v2/"

        fun create() : ArticleApi {

            val retrofit = BaseApi.createRetrofit(BASE_URL)

            return retrofit.create(ArticleApi::class.java)

        }
    }

}