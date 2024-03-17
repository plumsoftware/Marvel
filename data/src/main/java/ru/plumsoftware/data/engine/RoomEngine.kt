package ru.plumsoftware.data.engine

import android.content.Context
import androidx.room.Room
import ru.plumsoftware.data.model.dao.CharacterDatabase

fun roomEngine(context: Context): CharacterDatabase =

    Room.databaseBuilder(
        context,
        CharacterDatabase::class.java, "CharacterDatabase"
    ).build()