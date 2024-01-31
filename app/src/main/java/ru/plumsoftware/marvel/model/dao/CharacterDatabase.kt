package ru.plumsoftware.marvel.model.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.plumsoftware.data.model.dao.Character
import ru.plumsoftware.data.model.dao.DatabaseDao

@Database(
    entities = [Character::class],
    version = 1
)
abstract class CharacterDatabase : RoomDatabase() {
    abstract val dao: DatabaseDao
}