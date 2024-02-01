package ru.plumsoftware.data.storage

import ru.plumsoftware.data.model.dto.characters.CharacterRoot
import ru.plumsoftware.data.usecase.GetAllCharactersUseCase
import ru.plumsoftware.data.usecase.GetHeroByIdUseCase

class MarvelStorage(
    private val getAllCharactersUseCaseUse: GetAllCharactersUseCase,
    private val getHeroByIdUseCase: GetHeroByIdUseCase
) {

    suspend fun get(): CharacterRoot {
        return getAllCharactersUseCaseUse.execute()
    }

    suspend fun get(id: Int): CharacterRoot {
        return getHeroByIdUseCase.execute(id = id)
    }
}