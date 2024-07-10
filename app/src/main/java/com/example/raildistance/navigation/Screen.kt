package com.example.raildistance.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.raildistance.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int? = null,
    @DrawableRes val icon: Int? = null,
    @StringRes val contentDescription: Int? = null
) {
    companion object {
        private const val ROUTE_HOME = "home"
        const val ROUTE_STATIONS = "stations"
        private const val ROUTE_ACCOUNT = "settings"
    }

    data object Home : Screen(
        route = ROUTE_HOME,
        title = R.string.home,
        icon = R.drawable.ic_home,
        contentDescription = R.string.content_description_home_screen
    )

    data object Account : Screen(
        route = ROUTE_ACCOUNT,
        title = R.string.account,
        icon = R.drawable.ic_account,
        contentDescription = R.string.account
    )

    data object Stations : Screen(
        route = ROUTE_STATIONS,
        title = R.string.stations,
        icon = R.drawable.ic_stations,
        contentDescription = R.string.stations
    )
}