package com.ernazar.newsapp.domain.repository

import androidx.paging.PagingData
import com.ernazar.newsapp.data.model.Article

interface AppRepository {

    suspend fun getTopHeadlinePager(query: String): kotlinx.coroutines.flow.Flow<PagingData<Article>>

    suspend fun getEverythingPager(query: String): kotlinx.coroutines.flow.Flow<PagingData<Article>>

}