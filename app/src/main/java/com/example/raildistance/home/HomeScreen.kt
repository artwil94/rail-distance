package com.example.raildistance.home

import androidx.compose.foundation.background
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
import com.example.raildistance.composable.ActionButton
import com.example.raildistance.composable.ChangeSystemBarColor
import com.example.raildistance.composable.InputFieldType
import com.example.raildistance.composable.LoadingScreen
import com.example.raildistance.composable.SearchInputField
import com.example.raildistance.composable.StationItem
import com.example.raildistance.data.remote.StationDto
import com.example.raildistance.navigation.BottomBar
import com.example.raildistance.stations.StationsUIState
import com.example.raildistance.stations.StationsViewModel
import com.example.raildistance.ui.theme.KoTheme

@Composable
fun HomeScreen(navController: NavHostController, viewModel: StationsViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getTrainStations()
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
            HomeScreenContent(uiState = viewModel.uiState)
        }
    }
}

@Composable
fun HomeScreenContent(uiState: StationsUIState) {
    val trainStations = uiState.trainStations ?: emptyList()
    val firstStation =
        remember { mutableStateOf<StationDto?>(null) }
    val secondStation =
        remember { mutableStateOf<StationDto?>(null) }
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
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
                    SearchInputField(
                        text = firstStation.value?.name ?: "",
                        leadingIcon = R.drawable.ic_start_destination,
                        inputFieldType = InputFieldType.Disabled
                    )
                    Spacer(modifier = Modifier.height(KoTheme.kODimensions.paddingXL))
                    SearchInputField(
                        text = secondStation.value?.name ?: "",
                        leadingIcon = R.drawable.ic_end_destination,
                        trailingIcon = R.drawable.ic_arrows_up_down,
                        inputFieldType = InputFieldType.Disabled
                    )
                    Spacer(modifier = Modifier.height(KoTheme.kODimensions.paddingSeparator))
                    ActionButton(
                        text = "Calculate distance",
                        onClick = {},
                        modifier = Modifier.fillMaxWidth(),
                        inverted = true,
                        trailingIcon = R.drawable.ic_distance_between
                    )
                    Spacer(modifier = Modifier.height(KoTheme.kODimensions.padding))
                }
            }
            if (uiState.isLoading) {
                LoadingScreen()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    uiState.trainStations?.let { stations ->
                        val limitedStations = stations.take(4)
                        items(limitedStations.size) { index ->
                            val station = limitedStations[index]
                            StationItem(
                                station = station,
                                onClick = { firstStation.value = station })
                        }
                    }
                }
            }
        }
    }
}