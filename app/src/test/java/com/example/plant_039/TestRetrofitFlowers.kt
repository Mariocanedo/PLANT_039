package com.example.plant_039

import com.example.plant_039.Model.RetrofitFlower
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestRetrofitFlowers {

    // doble de prueba
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }



    @Test

    fun testRetrofit() {

        val expectedBaseUrl = mockWebServer.url("/").toString()

        val retrofit = Retrofit.Builder()
            .baseUrl(expectedBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        RetrofitFlower.retrofitInstance = retrofit
        val retrofitInstace = RetrofitFlower.retrofitInstance
        Assert.assertEquals(expectedBaseUrl, retrofitInstace.baseUrl().toString())

    }
}








