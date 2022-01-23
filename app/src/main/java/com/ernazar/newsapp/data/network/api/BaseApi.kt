package com.ernazar.newsapp.data.network.api

import com.ernazar.newsapp.utils.Config.API_KEY
import com.ernazar.newsapp.utils.Config.CONNECT_TIMEOUT
import com.ernazar.newsapp.utils.Config.READ_TIMEOUT
import com.ernazar.newsapp.utils.Config.WRITE_TIMEOUT
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BaseApi {

    companion object {

        fun createRetrofit(baseUrl: String): Retrofit {

            val okHttpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(Interceptor {
                    return@Interceptor onIntercept(it)
                })

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private fun onIntercept(chain: Interceptor.Chain): okhttp3.Response {
            var original = chain.request()
            val token = API_KEY
            val url = original.url().newBuilder().addQueryParameter("apiKey", token).build()
            original = original.newBuilder().url(url).build()
            return chain.proceed(original)

        }

    }

}