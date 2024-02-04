package ru.plumsoftware.data.model.uimodel

import kotlin.random.Random

data class Hero(
    override val heroId: Int? = null,
    override val heroColor: Long = Random.nextLong(from = 0xFF000000, until = 0xFFFFFFFF),
    override val heroNameResId: String? = null,
    override val heroQuoteResId: String? = null,
    override val heroImageResId: String? = null
) : ru.plumsoftware.domain.model.Hero