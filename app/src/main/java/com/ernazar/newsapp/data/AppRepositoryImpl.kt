package com.ernazar.newsapp.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.ernazar.newsapp.data.model.Article
import com.ernazar.newsapp.data.network.EverythingSource
import com.ernazar.newsapp.data.network.NewsPageSource
import com.ernazar.newsapp.data.network.api.ArticleService
import com.ernazar.newsapp.domain.repository.AppRepository
import com.ernazar.newsapp.utils.Config.DEFAULT_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class AppRepositoryImpl(private val articleService: ArticleService) : AppRepository {

    override suspend fun getTopHeadlinePager(query: String): Flow<PagingData<Article>> =
        Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE
            ),
            pagingSourceFactory = { NewsPageSource(articleService, query) }
        ).flow

    override suspend fun getEverythingPager(query: String): Flow<PagingData<Article>> =
        Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE
            ),
            pagingSourceFactory = { EverythingSource(articleService, query) }
        ).flow

}