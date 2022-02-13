package com.citesoftware.mediamonkstechchallenge.APIrest

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private val cliente = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(cliente)
        .build()

    fun <T> build(service: Class<T>): T {
        return retrofit.create(service)
    }
}