package ru.plumsoftware.marvel.model.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val heroId: Int,
    val name: String,
    val description: String
)
