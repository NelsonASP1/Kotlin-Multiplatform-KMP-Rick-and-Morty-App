package com.nelson.example.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

//Esto crea un modulo adicional por plataforma, uno para Ios otro para Android
expect fun platFormModule():Module

//inicializar koin con los modulos
fun initKoin(config: KoinAppDeclaration? = null){
    startKoin{
        //este parametro es opcional
        config?.invoke(this)
        modules(
            uiModule,
            domainModule,
            dataModule,
            //Este modulo es par inyectar dependencia que solo afecten a Ios y Android
            platFormModule()
        )
    }
}