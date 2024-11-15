package com.example.superheroe.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroeService {

    @GET("search/{name}")
    suspend fun searchByName(@Path("name") query:String) : Response<SuperheroesResponse>

    @GET("{id}")
    suspend fun findById(@Path("id") identifier:String) : Response<Superhero>
}