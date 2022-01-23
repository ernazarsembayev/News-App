package com.ernazar.newsapp.utils.di

import com.ernazar.newsapp.data.AppRepositoryImpl
import com.ernazar.newsapp.data.network.NewsPageSource
import com.ernazar.newsapp.data.network.ServerCommunicator
import com.ernazar.newsapp.data.network.api.ArticleService
import com.ernazar.newsapp.domain.repository.AppRepository
import org.koin.dsl.module

val dataModule = module {
    single { ServerCommunicator() }

//    single { AppDatabase.getInstance( get() ) }

    single<AppRepository> {
        AppRepositoryImpl(
            articleService = get()
        )
    }

    factory { params ->
        NewsPageSource(
            get(),
            params.get()
        )
    }

    factory { ArticleService.create() }
}