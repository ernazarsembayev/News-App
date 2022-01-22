package com.ernazar.newsapp.utils.di

import com.ernazar.newsapp.domain.usecases.GetEverythingUseCase
import com.ernazar.newsapp.domain.usecases.GetTopHeadlinesUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetEverythingUseCase(appRepository =  get())
    }

    factory {
        GetTopHeadlinesUseCase(appRepository =  get())
    }

}