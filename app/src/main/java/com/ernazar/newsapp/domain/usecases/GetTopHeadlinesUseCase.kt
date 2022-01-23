package com.ernazar.newsapp.domain.usecases

import androidx.paging.PagingData
import com.ernazar.newsapp.data.model.Article
import com.ernazar.newsapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class GetTopHeadlinesUseCase(private val appRepository: AppRepository) {

    suspend fun execute(query: String): Flow<PagingData<Article>> {

        return appRepository.getTopHeadlinePager(query)

    }

}