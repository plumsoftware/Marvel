package ru.plumsoftware.data.storage

import ru.plumsoftware.data.model.dto.characters.CharacterRoot
import ru.plumsoftware.data.usecase.GetAllCharactersUseCase

class MarvelStorage(
    private val getAllCharactersUseCaseUse: GetAllCharactersUseCase
) {

    suspend fun get(): CharacterRoot {
        return getAllCharactersUseCaseUse.execute()
    }
}