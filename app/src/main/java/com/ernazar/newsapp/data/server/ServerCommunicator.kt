package com.ernazar.newsapp.data.server

import com.ernazar.newsapp.data.server.api.ArticleApi
import com.ernazar.newsapp.data.server.response.getArticlesResponse.GetArticlesResponse
import retrofit2.Response

class ServerCommunicator {

    suspend fun getEverything(query: String): Response<GetArticlesResponse?> {
        val articleApi = ArticleApi.create()
        return articleApi.getEverything(query)
    }

    suspend fun getTopHeadline(query: String): Response<GetArticlesResponse?> {
        val articleApi = ArticleApi.create()
        return articleApi.getTopHeadlines(query)
    }

}