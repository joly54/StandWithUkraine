package com.fivesysdev.standwithukraine.di

import com.fivesysdev.standwithukraine.viewModels.StatisticViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { StatisticViewModel(get()) }
}
