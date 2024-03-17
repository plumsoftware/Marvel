package ru.plumsoftware.marvel.ui.presentation.heropage.state

import androidx.compose.runtime.Immutable
import ru.plumsoftware.data.model.uimodel.Hero

@Immutable
data class HeroState (
    val hero: Hero? = null
)