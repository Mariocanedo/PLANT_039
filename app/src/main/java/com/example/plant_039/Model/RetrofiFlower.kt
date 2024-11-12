package com.example.plant_039.Model

import com.example.plant_039.Model.Remote.FlowerApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFlower {

    companion object {

        private const val BASE_URL = "https://my-json-server.typicode.com/mauricioponce/TDApi/"

        lateinit var retrofitInstance: Retrofit
        fun retrofitInstance(): FlowerApi {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
                GsonConverterFactory.create()
            ).build()
            return retrofit.create(FlowerApi::class.java)
        }
    }

}