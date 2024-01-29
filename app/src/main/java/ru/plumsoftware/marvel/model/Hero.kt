package ru.plumsoftware.marvel.model

import androidx.compose.ui.graphics.Color

data class Hero(
    val heroColor: Color,
    val heroNameResId: String,
    val heroQuoteResId: String,
    val heroImageResId: String
)