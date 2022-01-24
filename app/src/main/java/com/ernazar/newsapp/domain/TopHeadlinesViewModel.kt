package com.ernazar.newsapp.domain

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ernazar.newsapp.data.model.Article
import com.ernazar.newsapp.domain.usecases.BookmarkUseCase
import com.ernazar.newsapp.domain.usecases.GetTopHeadlinesUseCase
import com.ernazar.newsapp.utils.Config.SERVER_QUERY_TIME
import com.ernazar.newsapp.utils.Config.TOPIC
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.component.inject

class TopHeadlinesViewModel(application: Application) : BaseViewModel(application) {

    private var updateJob: Job? = null
    private val scope = viewModelScope

    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase by inject()
    private val bookmarkUseCase: BookmarkUseCase by inject()

    private val _liveDataArticles = MutableLiveData<PagingData<Article>>()
    val liveDataArticles: LiveData<PagingData<Article>> = _liveDataArticles

    private val _liveDataBookMark = MutableLiveData<Article>()
    val liveDataBookMark: LiveData<Article> = _liveDataBookMark

    private val _liveDataArticle = MutableLiveData<Article>()
    val liveDataArticle: LiveData<Article> = _liveDataArticle

//    val news = Pager(PagingConfig(pageSize = 15)) {
//        newsPageSource
//    }.flow
//    .stateIn(scope, SharingStarted.Lazily, PagingData.empty())

    init {
        startUpdateArticles()
    }

    private fun startUpdateArticles() {
        stopUpdateArticles()
        updateJob = scope.launch {
            while(true) {

                val result = getTopHeadlinesUseCase.execute(TOPIC)
                result.collectLatest {
                    _liveDataArticles.postValue(it)
                }

                delay(SERVER_QUERY_TIME)
            }
        }
    }

    private fun stopUpdateArticles() {
        updateJob?.cancel()
        updateJob = null
    }

    fun articleSelect(article: Article){
        _liveDataArticle.value = article
    }

    override fun onCleared() {
        stopUpdateArticles()
        super.onCleared()
    }

    fun articleBookmark(article: Article){
//        CoroutineScope(Dispatchers.Default).launch {
//            _liveDataBookMark.postValue(bookmarkUseCase.execute(article))
//        }
    }
}