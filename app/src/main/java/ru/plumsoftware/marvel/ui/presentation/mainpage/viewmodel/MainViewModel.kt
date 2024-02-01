package ru.plumsoftware.marvel.ui.presentation.mainpage.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.plumsoftware.data.model.dao.Character
import ru.plumsoftware.data.model.dao.CharacterDatabase
import ru.plumsoftware.data.storage.MarvelStorage
import ru.plumsoftware.marvel.application.App
import ru.plumsoftware.data.model.uimodel.Hero
import ru.plumsoftware.marvel.ui.presentation.mainpage.state.MainState
import ru.plumsoftware.marvel.ui.presentation.mainpage.store.MainStore

class MainViewModel(
    private val output: (Output) -> Unit
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

    fun onOutput(o: Output) {
        output(o)
    }

    fun initAction(action: MainStore.Action) {
        when (action) {
            is MainStore.Action.DoMarvelRequest -> {
                viewModelScope.launch {
                    val doMarvelRequest: MutableList<Hero> = doMarvelRequest()
                    state.update {
                        it.copy(
                            isInternetConnectionError = !isInternetAvailable(),
                            listHeroes = doMarvelRequest
                        )
                    }
                }
            }
        }
    }

    private suspend fun doMarvelRequest(): MutableList<Hero> {
        val listHeroes = mutableListOf<Hero>()

        if (isInternetAvailable()) {
            val get = storage.get()
            if (get.code in 200..299)
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
                            characters = Character(
                                heroId = hero.heroId!!,
                                name = hero.heroNameResId!!,
                                description = hero.heroQuoteResId!!
                            )
                        )
                    }
                    return listHeroes
                } else return listHeroes
        } else {
            val allCharacters: List<Character> = db.dao.getAllCharacters()

            allCharacters.forEach {
                listHeroes.add(
                    Hero(
                        heroId = it.heroId,
                        heroNameResId = it.name,
                        heroQuoteResId = it.description
                    )
                )
            }

            return listHeroes
        }
    }

    private fun isInternetAvailable(): Boolean {
        val result: Boolean
        val context = App.INSTANCE
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }

    sealed class Output {
        data object NavigateTo : Output()
        data class ChangeSelectedHero(val selectedHero: Hero) : Output()
    }
}