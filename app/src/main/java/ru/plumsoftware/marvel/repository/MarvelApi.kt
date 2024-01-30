package ru.plumsoftware.marvel.repository

import androidx.annotation.Keep
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.plumsoftware.marvel.model.constant.ApiConstants
import ru.plumsoftware.marvel.model.dto.characters.CharacterRoot

@Keep
interface MarvelApi {
    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: String,
        @Query("apikey") apiKey: String = ApiConstants.PUBLIC_API_KEY,
        @Query("ts") ts: String = ApiConstants.ts,
        @Query("hash") hash: String = ApiConstants.hash()
    ): CharacterRoot

    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("limit") limit: String = ApiConstants.LIMIT,
        @Query("apikey") apiKey: String = ApiConstants.PUBLIC_API_KEY,
        @Query("ts") ts: String = ApiConstants.ts,
        @Query("hash") hash: String = ApiConstants.hash(),
    ): CharacterRoot
}