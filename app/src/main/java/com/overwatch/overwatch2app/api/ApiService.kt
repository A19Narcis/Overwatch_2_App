package com.overwatch.overwatch2app.api

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    //Get usuario
    @GET("players/{playerId}/")
    fun getInfoUsers(@Path("playerId") playerId: String) : Call<JsonObject>

    //Get heroes
    @GET("heroes/")
    fun getInfoHeroes() : Call<JsonArray>

}