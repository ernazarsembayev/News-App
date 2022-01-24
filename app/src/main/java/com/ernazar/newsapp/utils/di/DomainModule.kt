package com.ernazar.newsapp.utils.di

import com.ernazar.newsapp.domain.usecases.*
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetEverythingUseCase(appRepository =  get())
    }

    factory {
        GetTopHeadlinesUseCase(appRepository =  get())
    }

    factory {
        SaveArticleUseCase(appRepository =  get())
    }

    factory {
        DeleteArticleUseCase(appRepository =  get())
    }

    factory {
        BookmarkUseCase(appRepository =  get())
    }

    factory {
        GetBookmarksUseCase(appRepository =  get())
    }

}