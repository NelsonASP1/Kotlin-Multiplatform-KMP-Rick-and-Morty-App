package com.nelson.example

//Esto es un ejemplo de como podemos mostrar variables diferentes dependiendo de la plataforma
interface Platform {
    val name: String
}

expect fun getPlatform(): Platform