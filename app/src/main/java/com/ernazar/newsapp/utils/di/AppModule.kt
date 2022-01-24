package com.ernazar.newsapp.utils.di

import com.ernazar.newsapp.App
import com.ernazar.newsapp.domain.BookmarkViewModel
import com.ernazar.newsapp.domain.EverythingViewModel
import com.ernazar.newsapp.domain.TopHeadlinesViewModel
import com.ernazar.newsapp.presentation.fragment.EverythingFragment
import com.ernazar.newsapp.presentation.fragment.TopHeadlinesFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { App() }

    viewModel {
        EverythingViewModel(application = get())
    }

    viewModel {
        TopHeadlinesViewModel(application = get())
    }

    viewModel {
        BookmarkViewModel(application = get())
    }

    factory {
        TopHeadlinesFragment()
    }

    factory {
        EverythingFragment()
    }

}