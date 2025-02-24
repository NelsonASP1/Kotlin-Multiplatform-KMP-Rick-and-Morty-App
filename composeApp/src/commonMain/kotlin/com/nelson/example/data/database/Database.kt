package com.nelson.example.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.nelson.example.data.database.dao.UserPreferencesDAO
import com.nelson.example.data.database.entity.CharacterOfTheDayEntity

const val DATABASE_NAME = "rm_app_database.db"

expect object RickMortyCTor:RoomDatabaseConstructor<RickMortyDatabase>

@Database(entities = [CharacterOfTheDayEntity::class], version = 2)
//por el momento el al final CTor es necesario, tal vez cambie en el futuro
@ConstructedBy(RickMortyCTor::class)
abstract class RickMortyDatabase:RoomDatabase(){
    //DAO = Data Access Object
    abstract fun getPreferencesDao():UserPreferencesDAO

}