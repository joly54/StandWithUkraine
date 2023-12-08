package com.fivesysdev.standwithukraine.states

import com.fivesysdev.standwithukraine.models.Statistic

sealed class StatisticState {
    object Loading : StatisticState()
    data class Success(val statistic: Statistic) : StatisticState()
    data class Error(val message: String) : StatisticState()
}