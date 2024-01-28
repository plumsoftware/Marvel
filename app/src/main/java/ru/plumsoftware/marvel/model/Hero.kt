package ru.plumsoftware.marvel.model

import androidx.compose.ui.graphics.Color

data class Hero(
    val heroColor: Color,
    val heroNameResId: Int,
    val heroQuoteResId: Int,
    val heroImageResId: Int
)