package com.ernazar.newsapp.domain.repository

import com.ernazar.newsapp.data.server.response.getArticlesResponse.GetArticlesResponse
import retrofit2.Response

interface AppRepository {

    suspend fun getEverything(query: String): Response<GetArticlesResponse?>

    suspend fun getTopHeadline(query: String): Response<GetArticlesResponse?>

}