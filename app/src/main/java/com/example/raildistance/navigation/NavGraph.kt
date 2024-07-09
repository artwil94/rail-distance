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
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Account.route,
        ) {
            AccountScreen(navController = navController)
        }
        composable(
            route = Screen.Stations.route,
        ) {
            StationsScreen(navController = navController)
        }
    }
}