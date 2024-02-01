package ru.plumsoftware.marvel.ui.presentation.activity.store

import ru.plumsoftware.data.model.uimodel.Hero

sealed class MainActivityStore {
    sealed class Intent {
        data class ChangeSelectedHero(val selectedHero: Hero) : Intent()
    }
}