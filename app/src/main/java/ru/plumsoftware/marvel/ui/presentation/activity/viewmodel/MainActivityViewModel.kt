package ru.plumsoftware.marvel.ui.presentation.activity.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ru.plumsoftware.marvel.ui.presentation.activity.state.MainActivityState
import ru.plumsoftware.marvel.ui.presentation.activity.store.MainActivityStore

class MainActivityViewModel : ViewModel() {

    val state = MutableStateFlow(MainActivityState())

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
}