package com.example.raildistance.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.raildistance.R

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun RailDistanceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

    object KoTheme {
        val kOTypography: KoTypography
            @Composable get() = KoTypography()

        val kOShapes: KoShapes
            @Composable get() = KoShapes()
        val fonts: Fonts = Fonts()
        val kODimensions: KoDimensions
            @Composable get() = KoDimensions()
        val kOColors: KoColors
            @Composable get() = KoColors()

    }

    data class KoTypography(
        val inputField: TextStyle = TextStyle(
            fontSize = 16.sp,
            fontFamily = KoTheme.fonts.robotoLight,
            fontWeight = FontWeight(300),
            color = Color(0xFF34303D),
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            ),
        ),
        val labelSmall: TextStyle = TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(300),
            color = Color(0xFF696878),
            fontFamily = KoTheme.fonts.robotoLight
        ),
        val toolbar: TextStyle = TextStyle(
            fontSize = 24.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight(550),
            color = Color(0xFF333333),
            fontFamily = KoTheme.fonts.freigeistMedium
        ),
        val stationName: TextStyle = TextStyle(
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontFamily = KoTheme.fonts.freigeistMedium,
            fontWeight = FontWeight(550),
            color = Color(0xFF333333),
        ),
        val stationDescription: TextStyle = TextStyle(
            fontSize = 12.sp,
            lineHeight = 18.sp,
            fontFamily = KoTheme.fonts.robotoLight,
            fontWeight = FontWeight(300),
            color = Color(0xFF333333)
        ),
        val screenHeader: TextStyle = TextStyle(
            fontSize = 40.sp,
            lineHeight = 40.sp,
            fontFamily = KoTheme.fonts.freigeistMedium,
            fontWeight = FontWeight(550),
            color = Color.White,
        ),
        val brandTitle: TextStyle = TextStyle(
            fontSize = 24.sp,
            lineHeight = 24.sp,
            letterSpacing = 3.sp,
            fontFamily = KoTheme.fonts.freigeistMedium,
            fontWeight = FontWeight(550),
            color = Color.White,
        ),
        val actionButton: TextStyle = TextStyle(
            fontSize = 16.sp,
            lineHeight = 22.sp,
            fontFamily = KoTheme.fonts.freigeistMedium,
            fontWeight = FontWeight(550),
            color = Color(0xFF333333),
        ),
        val actionButtonWhite: TextStyle = TextStyle(
            fontSize = 16.sp,
            lineHeight = 22.sp,
            fontFamily = KoTheme.fonts.freigeistMedium,
            fontWeight = FontWeight(550),
            color = Color.White,
        ),
    )

    data class Fonts(
        val freigeistMedium: FontFamily = FontFamily(Font(R.font.freigeist_xconmedium)),
        val freigeistBold: FontFamily = FontFamily(Font(R.font.freigeist_xconbold)),
        val robotoMedium: FontFamily = FontFamily(Font(R.font.roboto_medium)),
        val robotoBold: FontFamily = FontFamily(Font(R.font.roboto_bold)),
        val robotoLight: FontFamily = FontFamily(Font(R.font.roboto_light)),
        val robotoRegular: FontFamily = FontFamily(Font(R.font.roboto_regular))
    )

    data class KoShapes(
        val inputField: Shape = RoundedCornerShape(10.dp),
        val stationItem: Shape = RoundedCornerShape(10.dp),
        val buttonDefaultShape: Shape = RoundedCornerShape(size = 1000.dp),

    )

    data class KoDimensions(
        val paddingXs: Dp = 4.dp,
        val paddingS: Dp = 8.dp,
        val paddingL: Dp = 12.dp,
        val padding: Dp = 16.dp,
        val paddingMedium: Dp = 20.dp,
        val paddingXL: Dp = 24.dp,
        val paddingXXL: Dp = 30.dp,
        val paddingSeparator: Dp = 50.dp,
        val screenHeaderHeight: Dp = 120.dp,
        val tabsHeight: Dp = 42.dp,
        val icon: Dp = 24.dp,
    )

    data class KoColors(
        val textStandard: Color = Color(0xFF333333),
        val shadow: Color = Color(0x0D000000),
        val backgroundScreen: Color = Color(0xFFECECEC),
        val screenHeader: Color = Color(0xFF1A237E),
        val selectedItem: Color = Color(0xFF4DB6AC),
        val surfaceBrand: Color = Color(0xFF00695C),
        val actionButton: Color = Color(0xFFEC407A),
    )