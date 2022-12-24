package com.bboard.android.ui.main.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bboard.android.data.dto.Products
import com.bboard.android.data.common.ApiState
import com.bboard.android.ui.home.HomeScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainNavHost(
    navController: NavHostController,
    contentState: LazyStaggeredGridState,
    products: ApiState<Products>,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = homeScreen.route,
        modifier = modifier
    ) {
        composable(homeScreen.route) {
            HomeScreen(products, contentState)
        }
    }
}