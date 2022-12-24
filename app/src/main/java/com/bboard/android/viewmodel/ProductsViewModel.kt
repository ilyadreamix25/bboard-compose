package com.bboard.android.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bboard.android.data.common.ApiState
import com.bboard.android.data.di.ProductsModule
import com.bboard.android.data.dto.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor() : ViewModel() {
    private val service = ProductsModule.provideService()

    private var _productsState = mutableStateOf(ApiState<Products>())
    val productsState: State<ApiState<Products>> = _productsState

    fun getProducts() {
        viewModelScope.launch {
            try {
                _productsState.value = ApiState(isLoading = true)

                val apiResponse = service.getProducts()

                if (apiResponse.isSuccessful) {
                    val result: Products = apiResponse.body()!!
                    _productsState.value = ApiState(data = result)
                } else {
                    _productsState.value = ApiState(
                        hasError = true,
                        errorMessage = "Unsuccessful response"
                    )
                }
            } catch (e: IOException) {
                _productsState.value = ApiState(
                    hasError = true,
                    errorMessage = "IO Exception: ${e.message}"
                )
            } catch (e: TimeoutException) {
                _productsState.value = ApiState(
                    hasError = true,
                    errorMessage = "Timeout Exception: ${e.message}"
                )
            } catch (e: HttpException) {
                _productsState.value = ApiState(
                    hasError = true,
                    errorMessage = "Http Exception: ${e.message}"
                )
            }
        }
    }
}