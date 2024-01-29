package ru.plumsoftware.marvel.utility

import androidx.compose.ui.graphics.Color
import ru.plumsoftware.marvel.model.constant.CharacterIds

fun getHeroColor(id: Int): Color {
    val colors = mapOf(
        CharacterIds.DEADPOOL_ID to Color(0xFF740308),
        CharacterIds.IRON_MAN_ID to Color(0xFFE01720),
        CharacterIds.THOR_ID to Color(0xFF4B0082),
        CharacterIds.DR_STRANGE_ID to Color(0xFFC18511),
    )

    return colors[id] ?: Color.Gray
}