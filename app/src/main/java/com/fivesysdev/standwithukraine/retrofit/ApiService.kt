package com.fivesysdev.standwithukraine.retrofit

import com.fivesysdev.standwithukraine.models.Statistic
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("statistics")
    fun getStatistics(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("date_from") dateFrom: String,
        @Query("date_to") dateTo: String
    ): Call<Statistic>
}
