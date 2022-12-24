package com.bboard.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.bboard.android.ui.main.MainScreen
import com.bboard.android.ui.theme.BBoardTheme
import com.bboard.android.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getProducts()

        setContent {
            BBoardTheme {
                MainScreen(viewModel.productsState.value)
            }
        }
    }
}
