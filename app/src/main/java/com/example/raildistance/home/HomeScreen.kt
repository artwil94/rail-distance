package com.example.raildistance.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.raildistance.stations.StationsViewModel

@Composable
fun HomeScreen(viewModel: StationsViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getTrainStations()
    }
    val trainStations = viewModel.uiState.trainStationDtos
    Scaffold(
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (viewModel.uiState.isLoading) {
                    item {
                        Text(text = "error")
                    }
                } else {
                    trainStations?.let {
                        items(it.size) { index ->
                            val station = trainStations[index]
                            Text(text = station.name ?: "")
                        }
                    }
                }
//                item {
//                    Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.padding))
//                }
            }
        }
    }
}