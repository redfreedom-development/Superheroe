package com.example.superheroe.data

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class SuperheroResponse (
    @SerializedName ("response") val response: String,
    @SerializedName ("results-for") val resultsFor: String,
    @SerializedName ("results") val results: List<Superhero>
) { }

data class Superhero (
    @SerializedName ("id") val id: String,
    @SerializedName ("name") val name: String,
    @SerializedName("powerstats") val powerstats: Stats,
    @SerializedName ("image") val image: Image
) { }

data class Image (
    @SerializedName ("url") val url: String,
) { }

data class Stats (
   @SerializedName("intelligence") val intelligence: Float,
     @SerializedName("strength") val strength: Float,
     @SerializedName("speed") val speed: Float,
    @SerializedName("durability") val durability: Float,
  @SerializedName("power") val power: Float,
   @SerializedName("combat") val combat: Float,
) { }