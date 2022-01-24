package com.ernazar.newsapp.domain

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ernazar.newsapp.data.model.Article
import com.ernazar.newsapp.domain.usecases.GetBookmarksUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class BookmarkViewModel(application: Application) : BaseViewModel(application) {

    private val getBookmarksUseCase: GetBookmarksUseCase by inject()

    private val _liveDataArticles = MutableLiveData<List<Article>>()
    val liveDataArticles: LiveData<List<Article>> = _liveDataArticles

//    init {
//        CoroutineScope(Dispatchers.Default).launch {
//            _liveDataArticles.postValue(getBookmarksUseCase.execute())
//        }
//    }

}