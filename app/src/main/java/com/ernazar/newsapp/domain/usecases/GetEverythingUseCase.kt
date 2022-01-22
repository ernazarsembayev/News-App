package com.ernazar.newsapp.domain.usecases

import com.ernazar.newsapp.data.server.response.getArticlesResponse.GetArticlesResponse
import com.ernazar.newsapp.domain.repository.AppRepository
import retrofit2.Response

class GetEverythingUseCase(private val appRepository: AppRepository) {

    suspend fun execute(query: String): Response<GetArticlesResponse?> {

        return appRepository.getEverything(query)

    }

}