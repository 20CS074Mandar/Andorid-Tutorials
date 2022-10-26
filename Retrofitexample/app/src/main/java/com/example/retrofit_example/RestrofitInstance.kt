package com.example.retrofit_example

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RestrofitInstance {

    val api:todoapi by lazy {
        Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com").addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(todoapi::class.java)
    }
}