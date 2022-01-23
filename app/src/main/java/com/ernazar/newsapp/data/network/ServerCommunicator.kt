package com.ernazar.newsapp.data.network

import com.ernazar.newsapp.data.network.api.ArticleService
import com.ernazar.newsapp.data.network.model.articlesResponse.ArticlesResponseDto
import retrofit2.Response

class ServerCommunicator {

    suspend fun getEverything(query: String): Response<ArticlesResponseDto?> {
        val articleApi = ArticleService.create()
        return articleApi.getEverything(query)
    }

    suspend fun getTopHeadline(query: String): Response<ArticlesResponseDto?> {
        val articleApi = ArticleService.create()
        return articleApi.getTopHeadlines(query, 1, 1)
    }

}