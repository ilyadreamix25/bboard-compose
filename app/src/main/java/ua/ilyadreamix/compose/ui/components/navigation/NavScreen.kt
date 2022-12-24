package ua.ilyadreamix.compose.ui.components.navigation

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Navigation screen configuration
 * @see TransparentNavBar
 * @see TransparentTopBar
 */
data class NavScreen(
    val route: String,
    val icon: ImageVector,
    val titleResId: Int
)
