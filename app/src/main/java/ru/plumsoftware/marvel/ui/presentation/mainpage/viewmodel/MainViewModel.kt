package ru.plumsoftware.marvel.ui.presentation.mainpage.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.plumsoftware.data.model.dao.CharacterDatabase
import ru.plumsoftware.data.storage.MarvelStorage
import ru.plumsoftware.marvel.model.uimodel.Hero
import ru.plumsoftware.marvel.ui.presentation.mainpage.state.MainState
import ru.plumsoftware.marvel.ui.presentation.mainpage.store.MainStore

class MainViewModel(
    private val output: (Output) -> Unit,
    private val action: (Unit) -> MainStore.Action,
) : ViewModel(), KoinComponent {

    private val db by inject<CharacterDatabase>()
    private val storage by inject<MarvelStorage>()

    val state = MutableStateFlow(MainState())

    fun onIntent(intent: MainStore.Intent) {
        when (intent) {
            is MainStore.Intent.OnHeroClick -> {
                state.update {
                    it.copy(
                        selectedHero = intent.hero
                    )
                }
            }

            is MainStore.Intent.OnScroll -> {
                state.update {
                    it.copy(
                        heroBackColor = intent.color
                    )
                }
            }
        }
    }

    fun initAction() {
        when (action.invoke(Unit)) {
            is MainStore.Action.DoMarvelRequest -> {
                viewModelScope.launch {
                    val doMarvelRequest: MutableList<Hero> = doMarvelRequest()
                    state.update {
                        it.copy(
                            listHeroes = doMarvelRequest
                        )
                    }
                }
            }
        }
    }

    private suspend fun doMarvelRequest(): MutableList<Hero> {
        val get = storage.get()
        val listHeroes = mutableListOf<Hero>()

        if (get.code == 200) {
            with(get.data!!.results) {
                for (i in this.indices) {
                    val results = this[i]
                    val hero = Hero(
                        heroId = results.id,
                        heroNameResId = results.name,
                        heroQuoteResId = results.description,
                        heroImageResId = "${
                            results.thumbnail?.path?.replace(
                                oldValue = "http",
                                newValue = "https"
                            )
                        }.${results.thumbnail?.extension}"
                    )

                    listHeroes.add(hero)
                    db.dao.upsertData(
                        characters = ru.plumsoftware.data.model.dao.Character(
                            heroId = hero.heroId!!,
                            name = hero.heroNameResId!!,
                            description = hero.heroQuoteResId!!
                        )
                    )
                }
            }
        }

        Log.i("MARVEL", listHeroes.toString())

        return listHeroes
    }

    fun onOutput(o: Output) {
        output(o)
    }

    sealed class Output {
        data object NavigateTo : Output()
    }
}