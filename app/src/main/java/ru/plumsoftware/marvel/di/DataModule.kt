package ru.plumsoftware.marvel.di

import org.koin.dsl.module
import ru.plumsoftware.data.engine.marvelEngine
import ru.plumsoftware.data.engine.roomEngine
import ru.plumsoftware.data.model.dao.CharacterDatabase
import ru.plumsoftware.data.repository.MarvelRepository
import ru.plumsoftware.data.repository.MarvelRepositoryImpl
import ru.plumsoftware.data.storage.MarvelStorage
import ru.plumsoftware.data.usecase.GetAllCharactersUseCase
import ru.plumsoftware.data.usecase.GetHeroByIdUseCase

internal val dataModule = module {
    single<MarvelRepository> { MarvelRepositoryImpl(marvelApi = marvelEngine()) }
    single<MarvelStorage> {
        MarvelStorage(
            getAllCharactersUseCaseUse = GetAllCharactersUseCase(marvelRepository = get()),
            getHeroByIdUseCase = GetHeroByIdUseCase(marvelRepository = get())
        )
    }

    single<CharacterDatabase> { roomEngine(context = get()) }
}