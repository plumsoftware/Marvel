package ru.plumsoftware.marvel.model.dto.characters

import com.squareup.moshi.Json
import ru.plumsoftware.data.model.dto.characters.Items_

data class Stories(
    @Json(name = "available") var available: Int? = null,
    @Json(name = "collectionURI") var collectionURI: String? = null,
    @Json(name = "items") var items: List<Items_> = listOf(),
    @Json(name = "returned") var returned: Int? = null
)
