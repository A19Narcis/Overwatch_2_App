package com.overwatch.overwatch2app.models.Hero.HeroStory

data class HeroStory(
    val summary: String,
    val media: HeroStoryMedia,
    val chapters: ArrayList<HeroStoryChap>
)