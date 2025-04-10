package com.brighamandersen.internalize.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColors(
    primary = PureBlack,
    onPrimary = PureWhite,
    background = PureWhite,
    onBackground = PureBlack,
    surface = PureWhite,
    onSurface = PureBlack,
    secondary = PureWhite,
    onSecondary = PureBlack,
)

private val DarkColorScheme = darkColors(
    primary = PureWhite,
    onPrimary = PureBlack,
    background = PureBlack,
    onBackground = PureWhite,
    surface = PureBlack,
    onSurface = PureWhite,
    secondary = PureBlack,
    onSecondary = PureWhite,
)

@Composable
fun Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colors = colors,
        content = content
    )
}
