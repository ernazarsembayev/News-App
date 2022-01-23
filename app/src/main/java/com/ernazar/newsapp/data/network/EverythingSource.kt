package com.ernazar.newsapp.data.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ernazar.newsapp.data.model.Article
import com.ernazar.newsapp.data.network.api.ArticleService
import com.ernazar.newsapp.utils.Config
import retrofit2.HttpException

class EverythingSource(
    private val articleService: ArticleService,
    private val query: String

) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        if(query.isEmpty()){
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }
        val page: Int = params.key ?: 1
        val pageSize: Int = params.loadSize.coerceAtMost(Config.MAX_PAGE_SIZE)

        val response = articleService.getEverything(query, page, pageSize)


        if (response.isSuccessful) {

            val articles = response.body()?.articles?.map { it.toArticle() } ?: emptyList()
            val nextKey = if (articles.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1

            Log.e("News 2", "articles: ${articles.size} response.body(): ${response.body()?.articles?.size}, nextKey: $nextKey, prevKey: $prevKey")
            return LoadResult.Page(articles, prevKey, nextKey)
        } else {
            return LoadResult.Error(HttpException(response))
        }
    }
}
