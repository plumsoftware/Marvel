package ru.plumsoftware.marvel.repository

import ru.plumsoftware.marvel.model.dto.characters.CharacterRoot

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