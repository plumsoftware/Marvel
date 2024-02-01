package ru.plumsoftware.marvel.model.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ru.plumsoftware.data.model.dao.Character

@Dao
interface DatabaseDao {

    @Query("DELETE FROM Character")
    suspend fun deleteAllData()

    @Query("SELECT * FROM Character")
    suspend fun getAllCharacters(): List<Character>

    @Upsert(entity = Character::class)
    suspend fun upsertData(characters: Character)
}