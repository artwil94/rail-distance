package com.example.raildistance.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.raildistance.account.AccountScreen
import com.example.raildistance.home.HomeScreen
import com.example.raildistance.stations.StationsScreen

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Home) {
        composable<Screen.Home>{
            HomeScreen(navController = navController)
        }
        composable<Screen.Account> {
            AccountScreen(navController = navController)
        }
        composable<Screen.Stations> {
            StationsScreen(navController = navController)
        }
    }
}