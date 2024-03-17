package ru.plumsoftware.marvel.ui.presentation.mainpage.store

import androidx.compose.ui.graphics.Color
import ru.plumsoftware.data.model.uimodel.Hero

sealed class MainStore {
    sealed class Intent {
        data class OnHeroClick(val hero: Hero) : Intent()
        data class OnScroll(val color: Color) : Intent()
    }

    sealed class Action {
        data object DoMarvelRequest : Action()
    }
}