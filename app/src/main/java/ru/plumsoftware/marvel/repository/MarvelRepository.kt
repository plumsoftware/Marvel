package ru.plumsoftware.marvel.repository

import ru.plumsoftware.marvel.model.dto.characters.CharacterRoot

interface MarvelRepository {
    suspend fun getHeroById(id: String): CharacterRoot

    suspend fun getAllCharacters(): CharacterRoot
}