package com.example.shoppy

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    // inside get give the endpoint (https://dummyjson.com/products)
    @GET("products")
    fun getProductData(): Call<MyData>
}