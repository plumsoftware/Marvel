package ru.plumsoftware.marvel.ui.presentation.activity.state

import androidx.compose.runtime.Immutable
import ru.plumsoftware.data.model.uimodel.Hero

@Immutable
data class MainActivityState(
    val selectedHero: Hero = Hero(),
    val messageId: Int = -1
)
