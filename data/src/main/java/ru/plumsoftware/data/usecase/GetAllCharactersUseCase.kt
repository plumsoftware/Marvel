package ru.plumsoftware.data.usecase

import ru.plumsoftware.data.model.dto.characters.CharacterRoot
import ru.plumsoftware.data.repository.MarvelRepository

class GetAllCharactersUseCase(private val marvelRepository: MarvelRepository) {
    suspend fun execute(): CharacterRoot {
        return marvelRepository.getAllCharacters()
    }
}