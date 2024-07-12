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
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.raildistance.R
import com.example.raildistance.composable.AutoCompleteSearchBar
import com.example.raildistance.composable.LoadingScreen
import com.example.raildistance.composable.SearchBarType
import com.example.raildistance.composable.StationItem
import com.example.raildistance.navigation.BottomBar
import com.example.raildistance.ui.theme.KoTheme

@ExperimentalMaterial3Api
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
                onCLoseCLick = { navController.popBackStack() },
                onValueChange = {
                    viewModel.filterStations(input = it)
                }
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun StationsScreenContent(
    uiState: StationsUIState,
    onCLoseCLick: () -> Unit = {},
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(KoTheme.koColors.screenHeader)
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = KoTheme.koDimensions.padding,
                        end = KoTheme.koDimensions.padding,
                        top = KoTheme.koDimensions.paddingXL
                    )
                    .systemBarsPadding(),
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.stations).uppercase(),
                    style = KoTheme.koTypography.brandTitle
                )
                Spacer(modifier = Modifier.height(KoTheme.koDimensions.paddingXL))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AutoCompleteSearchBar(
                        items = uiState.filteredStations ?: emptyList(),
                        leadingIcon = R.drawable.ic_search,
                        trailingIcon = R.drawable.ic_close,
                        placeholder = stringResource(id = R.string.enter_station_name),
                        onValueChange = { input ->
                            onValueChange.invoke(input)
                        },
                        searchBarType = SearchBarType.WithoutContent,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(id = R.string.close).uppercase(),
                        style = KoTheme.koTypography.close,
                        modifier = Modifier
                            .clickable {
                                onCLoseCLick.invoke()
                            },
                        textDecoration = TextDecoration.Underline
                    )
                }
                Spacer(modifier = Modifier.height(KoTheme.koDimensions.paddingXL))
            }
        }
        if (uiState.isLoading) {
            LoadingScreen()
        } else {
            if (uiState.filteredStations.isNullOrEmpty().not()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.ime)
                ) {
                    item {
                        Text(
                            modifier = Modifier.padding(
                                start = KoTheme.koDimensions.paddingL,
                                end = KoTheme.koDimensions.paddingL,
                                top = KoTheme.koDimensions.padding,
                                bottom = KoTheme.koDimensions.padding
                            ),
                            text = stringResource(id = R.string.results) + ":",
                            style = KoTheme.koTypography.stationName
                        )
                    }
                    uiState.filteredStations?.let { stations ->
                        items(stations.size) { index ->
                            val station = stations[index]
                            StationItem(station = station)
                        }
                    }
                }
            }
        }
    }
}