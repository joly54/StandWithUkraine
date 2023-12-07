package com.fivesysdev.standwithukraine.models

data class Record(
    val date: String,
    val day: Int,
    val increase: Increase,
    val resource: String,
    val stats: Stats,
    val war_status: WarStatus
)