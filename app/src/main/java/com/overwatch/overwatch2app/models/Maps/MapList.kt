package com.overwatch.overwatch2app.models.Maps

import android.widget.TextView
import com.google.gson.JsonArray

data class MapList(
    val name: String,
    val screenshot: String,
    val gamemodes: JsonArray,
    val location: String,
    val country_code: String,
    val qp: Boolean,
    val comp: Boolean,
    val arcade: Boolean
)
