package com.fivesysdev.standwithukraine.di

import com.fivesysdev.standwithukraine.data.ApiService
import com.fivesysdev.standwithukraine.viewModels.StatisticViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    val appModule = module {
        single {
            Retrofit.Builder().baseUrl("https://russianwarship.rip/api/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build().create(
                ApiService::class.java
            )
        }
        viewModel { StatisticViewModel(get()) }
    }
}