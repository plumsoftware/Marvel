package ru.plumsoftware.data.repository

import ru.plumsoftware.data.api.MarvelApi
import ru.plumsoftware.data.model.dto.characters.CharacterRoot

class MarvelRepositoryImpl(
    private val marvelApi: MarvelApi
) : MarvelRepository {

    override suspend fun getHeroById(id: String): CharacterRoot {
        return marvelApi.getCharacterById(characterId = id)
    }

    override suspend fun getAllCharacters(): CharacterRoot {
        return marvelApi.getAllCharacters()
    }
}