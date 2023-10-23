package com.overwatch.overwatch2app.models.Hero

import com.google.gson.JsonArray
import com.overwatch.overwatch2app.models.Hero.HeroStory.HeroStory

data class Hero(
    val name: String,
    val description: String,
    val portrait: String,
    val role: String,
    val location: String,
    val hitpoints: HeroHitpoints,
    val abilities: JsonArray,
    val story: HeroStory
)










