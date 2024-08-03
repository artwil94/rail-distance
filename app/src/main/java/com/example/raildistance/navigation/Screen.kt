package com.example.raildistance.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.raildistance.R
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Home : Screen()

    @Serializable
    data object Stations : Screen()

    @Serializable
    data object Account : Screen()
}

enum class BottomBarScreen(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    val route: Screen
) {
    HOME(label = R.string.home, icon = R.drawable.ic_home, Screen.Home),
    STATIONS(R.string.stations, icon = (R.drawable.ic_stations), Screen.Stations),
    ACCOUNT(R.string.account, icon = (R.drawable.ic_account), Screen.Account),
}