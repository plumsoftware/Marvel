package ru.plumsoftware.marvel.model.dto.characters

import com.squareup.moshi.Json

data class Urls(
    @Json(name = "type") var type: String? = null,
    @Json(name = "url") var url: String? = null
)
