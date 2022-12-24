package com.bboard.android.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.bboard.android.R
import com.bboard.android.data.dto.Products
import com.bboard.android.ui.main.navigation.MainNavHost
import com.bboard.android.ui.main.navigation.homeScreen
import com.bboard.android.ui.main.navigation.screens
import com.bboard.android.data.common.ApiState
import com.bboard.android.ui.theme.BBoardBlue
import kotlinx.coroutines.launch
import ua.ilyadreamix.compose.ui.components.navigation.TransparentNavBar
import ua.ilyadreamix.compose.ui.components.navigation.TransparentTopBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(products: ApiState<Products>) {
    val navController = rememberNavController()
    val contentState = rememberLazyStaggeredGridState()
    var isBottomBarVisible by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

    isBottomBarVisible = contentState.firstVisibleItemIndex < 3

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
            Scaffold(
                topBar = {
                    TransparentTopBar(
                        navController = navController,
                        defaultTextResource = R.string.home,
                        screens = screens,
                        mainScreen = homeScreen
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Rounded.Menu,
                                contentDescription = null
                            )
                        }
                    }
                },
                bottomBar = {
                    AnimatedVisibility(isBottomBarVisible) {
                        TransparentNavBar(
                            screens,
                            navController,
                            false
                        )
                    }
                },
                backgroundColor = Color.Transparent,
                floatingActionButtonPosition = FabPosition.End,
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = if (isBottomBarVisible) {
                            { /*TODO*/ }
                        } else {
                            {
                                scope.launch {
                                    contentState.animateScrollToItem(0)
                                }
                            }
                        },
                        backgroundColor = BBoardBlue,
                        contentColor = Color.White
                    ) {
                        Crossfade(isBottomBarVisible) {
                            if (it) Icon(
                                Icons.Rounded.Search,
                                null
                            )
                            else Icon(
                                Icons.Rounded.ArrowUpward,
                                null
                            )
                        }
                    }
                }
            ) {
                MainNavHost(
                    navController,
                    contentState,
                    products,
                    Modifier
                        .padding(it)
                        .fillMaxSize()
                        .systemBarsPadding()
                )
            }
        }
    }
}