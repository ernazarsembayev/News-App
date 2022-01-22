package com.ernazar.newsapp.utils.di

import com.ernazar.newsapp.App
import com.ernazar.newsapp.presentation.fragment.EverythingFragment
import com.ernazar.newsapp.presentation.fragment.TopHeadlinesFragment
import org.koin.dsl.module

val appModule = module {

    single { App() }

    factory {
        TopHeadlinesFragment()
    }

    factory {
        EverythingFragment()
    }

}