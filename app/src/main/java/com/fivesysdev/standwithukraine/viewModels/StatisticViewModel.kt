package com.fivesysdev.standwithukraine.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fivesysdev.standwithukraine.data.ApiService
import com.fivesysdev.standwithukraine.models.Statistic
import com.fivesysdev.standwithukraine.states.StatisticState
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StatisticViewModel(
    private val apiService: ApiService
) : ViewModel() {
    private val _statistic = MutableLiveData<Statistic?>()
    private val _statisticState = MutableLiveData<StatisticState>()
    private var _selectedDate: String = currentDate
    val state: LiveData<StatisticState>
        get() = _statisticState

    val statistic: MutableLiveData<Statistic?> = _statistic

    fun setDate(date: String) {
        _selectedDate = date
        fetchStatistic()
    }
    fun getSelectedDate(): String {
        return _selectedDate
    }

    private val currentDate: String
        get() {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return sdf.format(Date())
        }

    fun fetchStatistic() {
        viewModelScope.launch {
            if(_statistic.value?.data?.records?.firstOrNull()?.date == _selectedDate) {
                return@launch
            }
            _statisticState.value = StatisticState.Loading
            try {
                val response = apiService.getStatistics(0, 50, _selectedDate, _selectedDate)

                if (response != null) {
                    _statistic.value = response
                    _statisticState.value = StatisticState.Success(response)
                } else {
                    _statisticState.value = StatisticState.Error("Empty response")
                    _statistic.value = Statistic(null, "Empty response")
                }
            } catch (e: Exception) {
                _statisticState.value = StatisticState.Error(e.message.toString())
                _statistic.value = Statistic(null, e.message.toString())
                e.printStackTrace()
            }
        }
    }

}

