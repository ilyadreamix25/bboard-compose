package com.bboard.android.data.remote.repository

import com.bboard.android.data.dto.Products
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {
    @GET("products")
    suspend fun getProducts() : Response<Products>
}