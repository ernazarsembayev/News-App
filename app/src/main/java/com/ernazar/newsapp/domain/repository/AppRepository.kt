package com.ernazar.newsapp.domain.repository

import androidx.paging.PagingData
import com.ernazar.newsapp.data.model.Article

interface AppRepository {

    suspend fun getTopHeadlinePager(query: String): kotlinx.coroutines.flow.Flow<PagingData<Article>>

    suspend fun getEverythingPager(query: String): kotlinx.coroutines.flow.Flow<PagingData<Article>>

//    suspend fun saveArticle(article: Article): Article
//
//    suspend fun deleteArticle(article: Article): Article
//
//    suspend fun getArticle(articleUrl: String): Article?
//
//    suspend fun getBookmarks(): List<Article>

}