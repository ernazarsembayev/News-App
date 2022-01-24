package com.ernazar.newsapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ernazar.newsapp.data.model.Article
import com.ernazar.newsapp.data.network.EverythingSource
import com.ernazar.newsapp.data.network.TopHeadlinesSource
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
            pagingSourceFactory = { TopHeadlinesSource(articleService, query) }
        ).flow

    override suspend fun getEverythingPager(query: String): Flow<PagingData<Article>> =
        Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE
            ),
            pagingSourceFactory = { EverythingSource(articleService, query) }
        ).flow

//    override suspend fun saveArticle(article: Article): Article {
//        database.articleDao().insert(article.toArticleEntity())
//        return article
//    }
//
//    override suspend fun deleteArticle(article: Article): Article {
//        database.articleDao().delete(article.toArticleEntity())
//        return article
//    }
//
//    override suspend fun getArticle(articleUrl: String): Article? {
//        return database.articleDao().get(articleUrl)?.toArticle()
//    }
//
//    override suspend fun getBookmarks(): List<Article> {
//        return database.articleDao().getAll().map { it.toArticle() }
//    }

}