package ru.plumsoftware.domain.model

interface Hero {
    val heroId: Int?
    val heroColor: Long
    val heroNameResId: String?
    val heroQuoteResId: String?
    val heroImageResId: String?
}