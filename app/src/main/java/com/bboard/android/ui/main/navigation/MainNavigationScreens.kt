package com.bboard.android.ui.main.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import ua.ilyadreamix.compose.ui.components.navigation.NavScreen
import com.bboard.android.R

val homeScreen = NavScreen(
    "home",
    Icons.Rounded.Home,
    R.string.home
)

val screens = listOf(
    homeScreen
)
