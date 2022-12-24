package ua.ilyadreamix.compose.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Dark and light palettes for TransparentSystemBarsTheme
 * @see TransparentSystemBarsTheme {@link TransparentSystemBarsTheme}
 * @author IlyaDreamix
 */
data class Palette(
    val light: Colors,
    val dark: Colors
)

/**
 * Material theme with transparent system bars if available.
 * Checks if SDK version supports edge-to-edge screen, then applies it.
 * @param palette Default app dark and light palettes
 * @param setDarkTheme Apply dark theme
 * @param setSystemBarsTransparent Apply edge-to-edge screen if available
 * @param content Screen content
 * @author IlyaDreamix
 */
@Composable
fun TransparentSystemBarsTheme(
    palette: Palette,
    setDarkTheme: Boolean = isSystemInDarkTheme(),
    setSystemBarsTransparent: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = when (setDarkTheme) {
        true -> palette.dark
        false -> palette.light
    }

    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            if (!setSystemBarsTransparent) {
                window.statusBarColor = colors.background.toArgb()
                window.navigationBarColor = colors.background.toArgb()
            }

            val isEdgeToEdgeAvailable = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

            if (setSystemBarsTransparent && isEdgeToEdgeAvailable) {
                WindowCompat.setDecorFitsSystemWindows(window, false)
                window.statusBarColor = Color.Transparent.toArgb()
                window.navigationBarColor = Color.Transparent.toArgb()
            }

            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.isAppearanceLightStatusBars = !setDarkTheme
            insetsController.isAppearanceLightNavigationBars = !setDarkTheme
        }
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}