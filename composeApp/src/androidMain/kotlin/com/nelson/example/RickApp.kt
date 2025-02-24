package com.nelson.example

import android.app.Application
import com.nelson.example.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class RickApp : Application() {

    //Este fichero se creo para inicializar Koin en Android
    override fun onCreate() {
        super.onCreate()
        initKoin{
            //Estos ajustes son opcinales, este primero es un Log para ver la informacion recibida
            androidLogger()
            //inyectamos el contexto ya que algunos sitios web lo necesitan
            androidContext(this@RickApp)
        }
    }
}