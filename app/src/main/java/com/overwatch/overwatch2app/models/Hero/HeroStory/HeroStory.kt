package com.overwatch.overwatch2app.models.Hero.HeroStory

import com.google.gson.JsonArray

data class HeroStory(
    val summary: String,
    val media: HeroStoryMedia,
    val chapters: JsonArray
)