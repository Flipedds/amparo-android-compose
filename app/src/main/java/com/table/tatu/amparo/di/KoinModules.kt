package com.table.tatu.amparo.di
import com.table.tatu.amparo.repositories.AmparoRepository
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.table.tatu.amparo.services.AmparoService
import com.table.tatu.amparo.services.LocationService
import com.table.tatu.amparo.ui.viewmodels.PostScreenViewModel
import com.table.tatu.amparo.ui.viewmodels.LoginScreenViewModel
import com.table.tatu.amparo.ui.viewmodels.DenounceScreenViewModel
import com.table.tatu.amparo.ui.viewmodels.AmparoScreenViewModel
import com.table.tatu.amparo.ui.viewmodels.CadastroScreenViewModel
import com.table.tatu.amparo.ui.viewmodels.AjudaScreenViewModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.table.tatu.amparo.services.UserPreferences
import java.util.concurrent.TimeUnit


val appModule = module {
    viewModelOf(::PostScreenViewModel)
    viewModelOf(::LoginScreenViewModel)
    viewModelOf(::DenounceScreenViewModel)
    viewModelOf(::AmparoScreenViewModel)
    viewModelOf(::CadastroScreenViewModel)
    viewModelOf(::AjudaScreenViewModel)
    singleOf(::AmparoService)
    singleOf(::UserPreferences)
    singleOf(::LocationService)
}

val netWorkModule = module {
    single {
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
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
