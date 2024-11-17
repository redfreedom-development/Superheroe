package com.example.superheroe.utils

import com.example.superheroe.services.SuperheroeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {
    companion object {
        fun getRetrofit(): SuperheroeService {
            val retrofit = Retrofit.Builder()
                //.baseUrl("https://superheroapi.com/api/7252591128153666/")
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()



            return retrofit.create(SuperheroeService::class.java)
        }
    }
}