package com.ernazar.newsapp.data

import com.ernazar.newsapp.data.server.ServerCommunicator
import com.ernazar.newsapp.data.server.response.getArticlesResponse.GetArticlesResponse
import com.ernazar.newsapp.domain.repository.AppRepository
import retrofit2.Response

class AppRepositoryImpl(private val serverCommunicator: ServerCommunicator) : AppRepository {

    override suspend fun getEverything(query: String): Response<GetArticlesResponse?> {
        return serverCommunicator.getEverything(query)
    }

    override suspend fun getTopHeadline(query: String): Response<GetArticlesResponse?> {
        return serverCommunicator.getTopHeadline(query)
    }

}