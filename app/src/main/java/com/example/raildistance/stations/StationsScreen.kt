package com.example.raildistance.stations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.raildistance.R
import com.example.raildistance.composable.LoadingScreen
import com.example.raildistance.composable.SearchInputField
import com.example.raildistance.composable.StationItem
import com.example.raildistance.navigation.BottomBar
import com.example.raildistance.ui.theme.KoTheme

@Composable
fun StationsScreen(
    navController: NavHostController,
    viewModel: StationsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getTrainStations()
        viewModel.getStationKeywords()
    }
    Scaffold(
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            StationsScreenContent(
                uiState = viewModel.uiState,
                onCLoseCLick = { navController.popBackStack() })
        }
    }
}

@Composable
fun StationsScreenContent(uiState: StationsUIState, onCLoseCLick: () -> Unit = {}) {
    val inputField = remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(KoTheme.kOColors.screenHeader)
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = KoTheme.kODimensions.padding,
                        end = KoTheme.kODimensions.padding,
                        top = KoTheme.kODimensions.paddingXL
                    )
                    .systemBarsPadding()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SearchInputField(
                        text = inputField.value,
                        onValueChange = { text -> inputField.value = text },
                        leadingIcon = R.drawable.ic_search,
                        modifier = Modifier.width(300.dp)
                    )
                    Spacer(modifier = Modifier.width(KoTheme.kODimensions.padding))
                    Icon(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onCLoseCLick.invoke() },
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = stringResource(
                            id = R.string.content_description_close_station_searching
                        ),
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(KoTheme.kODimensions.paddingXL))
            }
        }
        if (uiState.isLoading) {
            LoadingScreen()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    Text(
                        modifier = Modifier.padding(
                            start = KoTheme.kODimensions.paddingL,
                            end = KoTheme.kODimensions.paddingL,
                            top = KoTheme.kODimensions.padding,
                            bottom = KoTheme.kODimensions.padding
                        ),
                        text = stringResource(id = R.string.results) + ":",
                        style = KoTheme.kOTypography.stationName
                    )
                }
                uiState.trainStations?.let { stations ->
                    items(stations.size) { index ->
                        val station = stations[index]
                        StationItem(station = station)
                    }
                }
            }
        }
    }

}