package com.ernazar.newsapp.domain

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ernazar.newsapp.R
import com.ernazar.newsapp.data.model.Article
import com.ernazar.newsapp.domain.usecases.BookmarkUseCase
import com.ernazar.newsapp.domain.usecases.DeleteArticleUseCase
import com.ernazar.newsapp.domain.usecases.GetEverythingUseCase
import com.ernazar.newsapp.domain.usecases.SaveArticleUseCase
import com.ernazar.newsapp.utils.Config.TOPIC
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class EverythingViewModel(application: Application) : BaseViewModel(application) {

    private val getEverythingUseCase: GetEverythingUseCase by inject()
    private val bookmarkUseCase: BookmarkUseCase by inject()

    private val _liveDataArticles = MutableLiveData<PagingData<Article>>()
    val liveDataArticles: LiveData<PagingData<Article>> = _liveDataArticles

    private val _liveDataBookMark = MutableLiveData<Article>()
    val liveDataBookMark: LiveData<Article> = _liveDataBookMark

    private val _liveDataArticle = MutableLiveData<Article>()
    val liveDataArticle: LiveData<Article> = _liveDataArticle

    init {
        viewModelScope.launch {
            val result = getEverythingUseCase.execute(TOPIC)
            result.collectLatest {
                _liveDataArticles.postValue(it)
            }
        }
    }

    fun articleSelect(article: Article){
        _liveDataArticle.value = article
    }

    fun articleBookmark(article: Article){
//        CoroutineScope(Dispatchers.Default).launch {
//            _liveDataBookMark.postValue(bookmarkUseCase.execute(article))
//        }
    }
}