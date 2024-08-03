package com.example.raildistance.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.raildistance.ui.theme.KoTheme


@ExperimentalComposeUiApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold {
        SetupNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
        ?: BottomBarScreen.HOME.route::class.qualifiedName.orEmpty()

    val currentRouteTrimmed by remember(currentRoute) {
        derivedStateOf { currentRoute.substringBefore("?") }
    }
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = Color.White
    ) {
        BottomBarScreen.entries.forEachIndexed { index, screen ->
            val isSelected by remember(currentRoute) {
                derivedStateOf { currentRouteTrimmed == screen.route::class.qualifiedName }
            }
            NavigationBarItem(
                modifier = Modifier
                    .clip(KoTheme.koShapes.buttonDefaultShape)
                    .background(
                        color = if (isSelected) KoTheme.koColors.screenHeader else Color.White,
                        shape = if (isSelected) KoTheme.koShapes.buttonDefaultShape else RoundedCornerShape(
                            0.dp
                        )
                    ),
                colors = NavigationBarItemColors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = Color.Black,
                    unselectedTextColor = Color.Black,
                    disabledIconColor = Color.Transparent,
                    disabledTextColor = Color.Transparent,
                ),
                selected = isSelected,
                onClick = {
                    navController.navigate(route = screen.route)
                },
                label = {
                    Text(
                        text = stringResource(id = screen.label),
                        style = if (isSelected) KoTheme.koTypography.bottomBarLabelSelect
                        else KoTheme.koTypography.bottomBarLabelUnSelect
                    )
                },
                icon = {
                    Icon(
                        modifier = Modifier
                            .size(KoTheme.koDimensions.icon),
                        painter = painterResource(id = screen.icon),
                        contentDescription = null,
                        tint = if (isSelected) Color.White else Color.Black
                    )
                }
            )
        }
    }
}