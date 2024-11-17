package com.table.tatu.amparo.di

import com.table.tatu.amparo.repositories.AmparoRepository
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.table.tatu.amparo.services.AmparoService
import com.table.tatu.amparo.ui.viewmodels.PostScreenViewModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    viewModelOf(::PostScreenViewModel)
    singleOf(::AmparoService)
}

val netWorkModule = module {
    single {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("URL_API")
            .client(client)
            .build()

        retrofit.create(AmparoRepository::class.java)
    }
}
