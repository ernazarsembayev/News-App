package com.ernazar.newsapp.domain

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ernazar.newsapp.data.model.Article
import com.ernazar.newsapp.domain.usecases.GetEverythingUseCase
import com.ernazar.newsapp.utils.Config.TOPIC
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class EverythingViewModel(application: Application) : BaseViewModel(application) {

    private val getEverythingUseCase: GetEverythingUseCase by inject()

    private val _liveDataArticles = MutableLiveData<PagingData<Article>>()
    val liveDataArticles: LiveData<PagingData<Article>> = _liveDataArticles

    init {
        viewModelScope.launch {
            val result = getEverythingUseCase.execute(TOPIC)
            result.collectLatest {
                _liveDataArticles.postValue(it)
            }
        }
    }

    fun articleSelect(article: Article){
        Log.e("Article", article.title.toString())
    }
}