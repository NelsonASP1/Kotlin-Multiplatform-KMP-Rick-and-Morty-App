package com.nelson.example.di

import com.nelson.example.domain.GetRandomCharacter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    //Inyectamos la funcion el GetRandomCharacter
    factoryOf(::GetRandomCharacter)

}