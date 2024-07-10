package com.example.raildistance.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.raildistance.R
import com.example.raildistance.composable.ActionButton
import com.example.raildistance.composable.AutoCompleteSearchBar
import com.example.raildistance.composable.ChangeSystemBarColor
import com.example.raildistance.navigation.BottomBar
import com.example.raildistance.stations.StationsUIState
import com.example.raildistance.stations.StationsViewModel
import com.example.raildistance.ui.theme.KoTheme

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(navController: NavHostController, viewModel: StationsViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState
    LaunchedEffect(key1 = Unit) {
        viewModel.getTrainStations()
        viewModel.getStationKeywords()
    }
    LaunchedEffect(key1 = uiState.firstStation, key2 = uiState.secondStation) {
        viewModel.resetDistanceCalculator()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.navigationBars),
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        ChangeSystemBarColor(statusBarColor = KoTheme.kOColors.screenHeader)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            HomeScreenContent(
                uiState = viewModel.uiState,
                onCalculateDistance = {
                    if (uiState.firstStation != null && uiState.secondStation != null) {
                        viewModel.calculateDistance(
                            firstStation = viewModel.uiState.firstStation!!,
                            secondStation = viewModel.uiState.secondStation!!
                        )
                    }
                })
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun HomeScreenContent(
    uiState: StationsUIState,
    viewModel: StationsViewModel = hiltViewModel(),
    onCalculateDistance: () -> Unit = {},
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = KoTheme.kOColors.screenHeader)
            ) {
                Column(
                    modifier = Modifier.padding(KoTheme.kODimensions.padding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.koleo).uppercase(),
                            style = KoTheme.kOTypography.brandTitle
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_train),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(KoTheme.kODimensions.paddingXXL))
                    AutoCompleteSearchBar(
                        keywords = uiState.keywords ?: emptyList(),
                        stations = uiState.trainStations ?: emptyList(),
                        leadingIcon = R.drawable.ic_start_destination,
                        trailingIcon = R.drawable.ic_close,
                        placeholder = stringResource(id = R.string.enter_station_name),
                        onItemClick = { trainStation ->
                            viewModel.onFirstStationSelect(trainStation)
                        }
                    )
                    Spacer(modifier = Modifier.height(KoTheme.kODimensions.paddingXL))
                    AutoCompleteSearchBar(
                        keywords = uiState.keywords ?: emptyList(),
                        stations = uiState.trainStations ?: emptyList(),
                        leadingIcon = R.drawable.ic_end_destination,
                        trailingIcon = R.drawable.ic_close,
                        placeholder = stringResource(id = R.string.enter_station_name),
                        onItemClick = { trainStation ->
                            viewModel.onSecondStationSelect(trainStation)
                        }
                    )
                    Spacer(modifier = Modifier.height(KoTheme.kODimensions.paddingSeparator))
                    ActionButton(
                        text = "Calculate distance",
                        onClick = {
                            onCalculateDistance.invoke()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        inverted = true,
                        trailingIcon = R.drawable.ic_distance_between
                    )
                    Spacer(modifier = Modifier.height(KoTheme.kODimensions.padding))
                }
            }
            if (uiState.distance.isNullOrBlank().not()) {
                Spacer(modifier = Modifier.height(KoTheme.kODimensions.paddingSeparator))
                Text(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = KoTheme.kODimensions.padding)
                        .clickable {
                            viewModel.resetDistanceCalculator()
                        },
                    text = stringResource(id = R.string.clear),
                    style = KoTheme.kOTypography.clearCTA,
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = stringResource(id = R.string.distance),
                    style = KoTheme.kOTypography.distance
                )
                Spacer(modifier = Modifier.height(KoTheme.kODimensions.paddingS))
                Text(
                    text = stringResource(id = R.string.km, uiState.distance.toString()),
                    style = KoTheme.kOTypography.stationName
                )
                Row(
                    modifier = Modifier
                        .padding(KoTheme.kODimensions.padding)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = uiState.firstStation?.name ?: "",
                        style = KoTheme.kOTypography.stationName
                    )
                    Icon(
                        modifier = Modifier.weight(1f),
                        painter = painterResource(id = R.drawable.ic_distance_between),
                        contentDescription = stringResource(
                            id = R.string.distance
                        ),
                        tint = Color.Red
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = uiState.secondStation?.name ?: "",
                        style = KoTheme.kOTypography.stationName
                    )
                }
            }
        }
    }
}