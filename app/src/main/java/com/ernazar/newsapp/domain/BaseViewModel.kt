package com.ernazar.newsapp.domain

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import com.ernazar.newsapp.App
import org.koin.core.component.KoinComponent

abstract class BaseViewModel(application: Application) : AndroidViewModel(application),
    KoinComponent {
        fun getContext() = getApplication<App>()
        fun getString(@StringRes id: Int): String = getContext().getString(id)
}