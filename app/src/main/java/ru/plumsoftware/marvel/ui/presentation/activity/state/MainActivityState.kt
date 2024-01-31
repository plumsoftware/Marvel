package ru.plumsoftware.marvel.ui.presentation.activity.state

import ru.plumsoftware.marvel.model.uimodel.Hero

data class MainActivityState(
    val selectedHero: Hero = Hero()
)
