package com.fivesysdev.standwithukraine.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fivesysdev.standwithukraine.models.Statistic
import com.fivesysdev.standwithukraine.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StatisticViewModel : ViewModel() {
    private val apiService = RetrofitClient.apiService

    private val _statistic = MutableLiveData<Statistic?>()
    private val _isDataLoaded = MutableLiveData<Boolean>()

    val statistic: MutableLiveData<Statistic?> = _statistic
    val isDataLoaded: MutableLiveData<Boolean> = _isDataLoaded

    private val currentDate: String
        get() {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return sdf.format(Date())
        }

    fun fetchStatistic(
        offset: Int = 0,
        limit: Int = 50,
        dateFrom: String = currentDate,
        dateTo: String = currentDate
    ) {
        val call = apiService.getStatistics(offset, limit, dateFrom, dateTo)

        call.enqueue(object : Callback<Statistic> {
            override fun onResponse(call: Call<Statistic>, response: Response<Statistic>) {
                if (response.isSuccessful) {
                    _statistic.value = response.body()
                    _isDataLoaded.value = true
                    println(response.body())
                    println("Success isDataLoaded: ${_isDataLoaded.value}")
                } else {
                    _statistic.value = Statistic(null, response.message())
                    println("Error isDataLoaded: ${_isDataLoaded.value}")
                }
            }

            override fun onFailure(call: Call<Statistic>, t: Throwable) {
                _statistic.value = Statistic(null, t.message.toString())
                println(t.message)
                println("Error isDataLoaded: ${_isDataLoaded.value}")
            }
        }
        )
    }
}
