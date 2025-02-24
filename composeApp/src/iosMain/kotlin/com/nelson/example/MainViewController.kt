package com.nelson.example

import androidx.compose.ui.window.ComposeUIViewController
import com.nelson.example.di.initKoin

//inicializamos Koin
fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }