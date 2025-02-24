package com.nelson.example.data

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.nelson.example.data.database.DATABASE_NAME
import com.nelson.example.data.database.RickMortyDatabase
import kotlinx.coroutines.Dispatchers

fun getDatabase(context: Context):RickMortyDatabase{

    val dbFile = context.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<RickMortyDatabase>(context,dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}