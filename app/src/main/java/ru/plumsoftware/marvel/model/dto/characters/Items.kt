package ru.plumsoftware.marvel.model.dto.characters

import com.squareup.moshi.Json

data class Items(
    @Json(name = "resourceURI") var resourceURI: String? = null,
    @Json(name = "name") var name: String? = null
)
