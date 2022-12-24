package com.bboard.android.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ua.ilyadreamix.compose.ui.theme.Palette
import ua.ilyadreamix.compose.ui.theme.TransparentSystemBarsTheme

private val appPalette = Palette(
    light = lightColors(
        primary = BBoardBlue,
        background = Color.White
    ),
    dark = darkColors(
        primary = BBoardBlue,
        background = Color.Black
    )
)

@Composable
fun BBoardTheme(content: @Composable () -> Unit) {
    TransparentSystemBarsTheme(
        palette = appPalette,
        setSystemBarsTransparent = false
    ) { content() }
}