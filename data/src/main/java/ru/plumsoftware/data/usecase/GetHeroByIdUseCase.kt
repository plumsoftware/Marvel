package ru.plumsoftware.data.usecase

import ru.plumsoftware.data.model.dto.characters.CharacterRoot
import ru.plumsoftware.data.repository.MarvelRepository

class GetHeroByIdUseCase(private val marvelRepository: MarvelRepository) {
    suspend fun execute(id: Int): CharacterRoot {
        return marvelRepository.getHeroById(id = id.toString())
    }
}