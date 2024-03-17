package ru.plumsoftware.marvel.ui.presentation.heropage.viewmodel

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.plumsoftware.data.model.uimodel.Hero
import ru.plumsoftware.marvel.ui.presentation.heropage.state.HeroState

@Immutable
class HeroViewModel(
    hero: Hero,
    private val output: (Output) -> Unit,
) : ViewModel() {
    val state = MutableStateFlow(HeroState(hero = hero))

    fun onOutput(o: Output) {
        output(o)
    }

    sealed class Output {
        data object OnBackClicked : Output()
    }
}