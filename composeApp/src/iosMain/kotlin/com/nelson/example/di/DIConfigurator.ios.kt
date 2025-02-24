package com.nelson.example.di

import com.nelson.example.data.database.RickMortyDatabase
import com.nelson.example.data.getDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platFormModule(): Module {
    return module {

        single<RickMortyDatabase> { getDatabase()  }
    }
}