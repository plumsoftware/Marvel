package ru.plumsoftware.data.model.dto.characters

import com.squareup.moshi.Json

data class Items_(
    @Json(name = "resourceURI")  var resourceURI: String? = null,
    @Json(name = "name")  var name: String? = null,
    @Json(name = "type")  var type: String? = null
)
