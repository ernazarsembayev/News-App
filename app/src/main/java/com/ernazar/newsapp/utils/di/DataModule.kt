package com.ernazar.newsapp.utils.di

import com.ernazar.newsapp.data.AppRepositoryImpl
import com.ernazar.newsapp.data.server.ServerCommunicator
import com.ernazar.newsapp.domain.repository.AppRepository
import org.koin.dsl.module

val dataModule = module {
    single { ServerCommunicator() }

//    single { AppDatabase.getInstance( get() ) }

    single<AppRepository> {
        AppRepositoryImpl(
            serverCommunicator = get()
        )
    }
}