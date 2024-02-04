package ru.plumsoftware.marvel.ui.presentation.mainpage.state

import androidx.compose.ui.graphics.Color
import ru.plumsoftware.data.model.uimodel.Hero

data class MainState(
    val useDark: Boolean = true,
    val listHeroes: MutableList<Hero> = mutableListOf(),
    val selectedHero: Hero? = null,
    val isInternetConnectionError: Boolean = false,
    val heroBackColor: Color = Color(Hero().heroColor)
)