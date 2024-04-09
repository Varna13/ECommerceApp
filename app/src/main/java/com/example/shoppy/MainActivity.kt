package com.example.shoppy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppy.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var productAdapter: ProductItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // create the retrofit builder to acces the data from Api
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java) // api interface which we are using for the communication purpose

        // Access the data using the retrofit builder
        val retrofitData = retrofitBuilder.getProductData()

        // enqueue - Asynchronously send the request and notify callback of its response

        retrofitData.enqueue(object : Callback<MyData?>{
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                // write what we want to do if the api call is success
                val responseBody = response.body() // get the response from api
                val productArray = responseBody?.products!! // we need to get the data from  array of products

                productAdapter = ProductItemAdapter(productArray)
                binding.rvProducts.apply {
                    adapter = productAdapter
                    layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                }
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                // write what we want to do if api call is a failure

                Log.d("TAG", "onFailure: ${t.message}")
            }

        })
    }
}