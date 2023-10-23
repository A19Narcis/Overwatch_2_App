package com.overwatch.overwatch2app.api

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.overwatch.overwatch2app.models.Maps.MapList
import com.overwatch.overwatch2app.models.Modes.ModeList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    //Get usuario
    @GET("players/{playerId}/")
    fun getInfoUsers(@Path("playerId") playerId: String): Call<JsonObject>

    //Get heroes
    @GET("heroes/")
    fun getInfoHeroes(): Call<JsonArray>

    //Get un hero
    @GET("heroes/{hero_key}/")
    fun getInfoOneHero(@Path("heroKey") heroKey: String): Call<JsonObject>

    //Get mapas
    @GET("maps/")
    fun getInfoMaps(): Call<JsonArray>

    //Get modos
    @GET("gamemodes/")
    fun getInfoModes(): Call<JsonArray>
}