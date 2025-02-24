package com.nelson.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nelson.example.data.database.entity.CharacterOfTheDayEntity

@Dao
interface UserPreferencesDAO {
    //Query para recuperar o buscar en la tabla el caracter del mes
    @Query("SELECT * FROM characterOfTheDay")
    suspend fun getCharacterOFTheDayBD():CharacterOfTheDayEntity?

    //Metodo para insertar los datos del caracter del mes
    @Insert(entity = CharacterOfTheDayEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacter(characterOfTheDayEntity: CharacterOfTheDayEntity)
}