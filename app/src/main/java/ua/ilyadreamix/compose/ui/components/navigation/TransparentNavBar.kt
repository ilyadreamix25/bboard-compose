package ua.ilyadreamix.compose.ui.components.navigation

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ua.ilyadreamix.compose.ui.components.other.RoundedSpacer

/**
 * Transparent navigation bar
 * @param screens Navigation bar screens
 * @param navController Navigation host controller
 * @param addTextBelowIcon Add title below icon
 * @author IlyaDreamix
 */
@Composable
fun TransparentNavBar(
    screens: List<NavScreen>,
    navController: NavHostController,
    addTextBelowIcon: Boolean = false
) {
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Row(
        modifier = Modifier
            .systemBarsPadding()
            .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
            .background(Color.Transparent)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            AddItem(screen, currentDestination, navController, addTextBelowIcon)
        }
    }
}

/**
 * Transparent navigation bar item
 * @param screen Navigation bar screen
 * @param currentDestination Current screen
 * @param navController Navigation host controller
 * @param addTextBelowIcon Add title below icon
 * @author IlyaDreamix
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddItem(
    screen: NavScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
    addTextBelowIcon: Boolean = false
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    var clickSelected by remember { mutableStateOf(false) }

    val clickScale by animateFloatAsState(if (clickSelected) .8f else 1f)
    val indicatorAlpha by animateFloatAsState(if (selected) 1f else 0f)

    Box(
        modifier = Modifier
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        clickSelected = true
                    }

                    MotionEvent.ACTION_UP -> {
                        clickSelected = false
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    }

                    MotionEvent.ACTION_CANCEL -> {
                        clickSelected = false
                    }
                }
                true
            }
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
                .scale(clickScale)
                .alpha(if (selected) 1f else .3f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = screen.icon,
                contentDescription = null,
                modifier = Modifier.size(28.dp).padding(bottom = 4.dp),
                tint = if (selected) MaterialTheme.colors.primary else Color.Unspecified
            )

            if (addTextBelowIcon) {
                Text(
                    text = stringResource(screen.titleResId),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 4.dp),
                    color = if (selected) MaterialTheme.colors.primary else Color.Unspecified
                )
            }

            RoundedSpacer(
                Modifier.width(12.dp),
                MaterialTheme.colors.primary.copy(indicatorAlpha)
            )
        }
    }
}
