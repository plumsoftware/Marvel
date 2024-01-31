package ru.plumsoftware.data.model.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Character::class],
    version = 1
)
abstract class CharacterDatabase : RoomDatabase() {
    abstract val dao: DatabaseDao
}