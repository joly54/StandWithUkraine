package com.fivesysdev.standwithukraine.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fivesysdev.standwithukraine.models.Record
import com.fivesysdev.standwithukraine.models.Statistic
import com.fivesysdev.standwithukraine.viewModels.StatisticViewModel

class MainScreen {
    @Composable
    private fun cardGenerator(
        label: String,
        totalCount: Int,
        increaseCount: Int,
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium,
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
//                    .weight(1f)
                ,
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = label, fontWeight = FontWeight.Bold)
                Text(text = "$totalCount(+${increaseCount})")
            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DisplayData(
        record: Record
    ) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        val cardsData = listOf(
            Triple("Personnal Units",  record.stats.personnel_units, record.increase.personnel_units),
            Triple("Tanks",  record.stats.tanks, record.increase.tanks),
            Triple("Artillery",  record.stats.artillery_systems, record.increase.artillery_systems),
            Triple("MLRS",  record.stats.mlrs, record.increase.mlrs),
            Triple("AA Warfare Systems",  record.stats.aa_warfare_systems, record.increase.aa_warfare_systems),
            Triple("Planes",  record.stats.planes, record.increase.planes),
            Triple("Helicopters",  record.stats.helicopters, record.increase.helicopters),
            Triple("Fuel Tanks",  record.stats.vehicles_fuel_tanks, record.increase.vehicles_fuel_tanks),
            Triple("Warships caters",  record.stats.warships_cutters, record.increase.warships_cutters),
            Triple("Cruse Missiles",  record.stats.cruise_missiles, record.increase.cruise_missiles),
            Triple("UAV Systems",  record.stats.uav_systems, record.increase.uav_systems),
            Triple("Submarines",  record.stats.submarines, record.increase.submarines),
            Triple("ATGM/SRBM Systems",  record.stats.atgm_srbm_systems, record.increase.atgm_srbm_systems),
        )
        Scaffold (
            Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "${record.date} (${record.day})",
                        )
                    },
                    scrollBehavior = scrollBehavior
                )
            }
        ){
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                items(cardsData.size) { index ->
                    cardGenerator(
                        cardsData[index].first,
                        cardsData[index].second,
                        cardsData[index].third
                    )
                }
            }
        }
    }

    @Composable
    fun Screen() {
        val viewModel = StatisticViewModel()
        val isReady by viewModel.isDataLoaded.observeAsState(initial = false)
        val statistic by viewModel.statistic.observeAsState(initial = Statistic(null, ""))
        LaunchedEffect(key1 = Unit) {
            viewModel.fetchStatistic()
        }
        Text("Is data loaded: ${isReady}")
        if (isReady) {
            statistic?.data?.records?.firstOrNull()?.let {
                DisplayData(it)
            }
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }
}