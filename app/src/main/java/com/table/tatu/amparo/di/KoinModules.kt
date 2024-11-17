package com.table.tatu.amparo.di

import com.table.tatu.amparo.repositories.AmparoRepository
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.converter.moshi.MoshiConverterFactory
import com.table.tatu.amparo.services.AmparoService
import com.table.tatu.amparo.ui.viewmodels.HomeScreenViewModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit


val appModule = module {
    viewModelOf(::HomeScreenViewModel)
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
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("URL_API")
            .client(client)
            .build()

        retrofit.create(AmparoRepository::class.java)
    }
}
