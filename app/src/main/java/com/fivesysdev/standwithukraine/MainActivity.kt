package com.fivesysdev.standwithukraine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fivesysdev.standwithukraine.screens.MainScreen
import com.fivesysdev.standwithukraine.ui.theme.StandWithUkraineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StandWithUkraineTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StandWithUkraineTheme {
                        Scaffold {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(it)
                                    .border(1.dp, MaterialTheme.colorScheme.primary)
                            ) {
                                MainScreen().Screen()
                            }

                        }
                    }
                }
            }
        }
    }
}