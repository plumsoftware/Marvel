package ru.plumsoftware.marvel.ui.presentation.activity.viewmodel

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.plumsoftware.data.model.dto.characters.CharacterRoot
import ru.plumsoftware.data.model.uimodel.Hero
import ru.plumsoftware.data.storage.MarvelStorage
import ru.plumsoftware.marvel.ui.presentation.activity.state.MainActivityState
import ru.plumsoftware.marvel.ui.presentation.activity.store.MainActivityStore
import ru.plumsoftware.marvel.utility.isInternetAvailable

@Immutable
class MainActivityViewModel(
    heroId: Int
) : ViewModel(), KoinComponent {

    private val marvelStorage by inject<MarvelStorage>()

    val state = MutableStateFlow(
        MainActivityState(
            messageId = heroId
        )
    )

    init {
        if (heroId > 0 && isInternetAvailable()) {
            viewModelScope.launch {
                val get: CharacterRoot = marvelStorage.get(id = heroId)
                with(get.data!!.results[0]) {
                    state.update {
                        it.copy(
                            selectedHero = Hero(
                                heroId = id,
                                heroNameResId = name,
                                heroQuoteResId = description,
                                heroImageResId = "${
                                    thumbnail?.path?.replace(
                                        "http",
                                        "https"
                                    )
                                }.${thumbnail?.extension}"
                            )
                        )
                    }
                }
            }
        }
    }

    fun onIntent(intent: MainActivityStore.Intent) {
        when (intent) {
            is MainActivityStore.Intent.ChangeSelectedHero -> {
                state.update {
                    it.copy(
                        selectedHero = intent.selectedHero
                    )
                }
            }
        }
    }

    sealed class Output {
        data object PushHeroPage : Output()
    }
}