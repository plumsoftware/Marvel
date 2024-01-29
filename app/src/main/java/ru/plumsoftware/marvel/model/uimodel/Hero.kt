package ru.plumsoftware.marvel.model.uimodel

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

data class Hero(
    val heroId: Int? = null,
    val heroColor: Color = Color(Random.nextLong(from = 0xFF000000, until = 0xFFFFFFFF)),
    val heroNameResId: String? = null,
    val heroQuoteResId: String? = null,
    val heroImageResId: String? = null
)