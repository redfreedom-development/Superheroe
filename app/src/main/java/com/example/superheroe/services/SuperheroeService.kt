package com.example.superheroe.services

import com.example.superheroe.data.Superhero
import com.example.superheroe.data.SuperheroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroeService {

    @GET("search/{name}")
    suspend fun searchByName(@Path("name") query:String) : SuperheroResponse

    @GET("{id}")
    suspend fun findById(@Path("id") identifier:String) : Superhero
}