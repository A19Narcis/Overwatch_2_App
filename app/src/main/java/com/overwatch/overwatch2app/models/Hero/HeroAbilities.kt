package com.overwatch.overwatch2app.models.Hero

import com.overwatch.overwatch2app.models.Hero.HeroVideo.HeroVideo

data class HeroAbilities(
    val name: String,
    val description: String,
    val icon: String,
    val video: HeroVideo
)