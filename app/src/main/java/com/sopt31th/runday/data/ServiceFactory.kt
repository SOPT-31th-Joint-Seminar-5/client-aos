package com.sopt31th.runday.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceFactory {
    private const val BASE_URL = "http://43.201.119.48:3000/"

    val rundayService: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
