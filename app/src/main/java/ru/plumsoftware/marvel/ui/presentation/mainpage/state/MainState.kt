package ru.plumsoftware.marvel.ui.presentation.mainpage.state

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import ru.plumsoftware.marvel.model.uimodel.Hero

data class MainState(
    val useDark: Boolean = true,
    val navHostController: NavHostController? = null,
    val listHeroes: MutableList<Hero> = mutableListOf(),
    val selectedHero: Hero? = null,
    val isInternetConnectionError: Boolean = false,
    val heroBackColor: Color = Hero().heroColor
)