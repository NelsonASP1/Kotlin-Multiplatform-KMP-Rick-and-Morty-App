package com.nelson.example

import androidx.compose.material3.Text
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

//Todo esto es para inicializar los parametros de la aplicacion de escritorio
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Rick test"
    ){
        Text("Hola")
    }
}