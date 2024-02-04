package ru.plumsoftware.data.repository

import ru.plumsoftware.data.model.dto.characters.CharacterRoot

interface MarvelRepository {
    suspend fun getHeroById(id: String): CharacterRoot

    suspend fun getAllCharacters(): CharacterRoot
}