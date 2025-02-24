package com.nelson.example.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.nelson.example.ui.detail.CharacterDetailViewModel
import com.nelson.example.ui.home.characters.CharactersViewModel
import com.nelson.example.ui.home.episodes.EpisodesViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    //inyectamos a este modulo el ViewModel de Episodios
    viewModelOf(::EpisodesViewModel)
    //inyectamos a este modulo el ViewModel de Characters
    viewModelOf(::CharactersViewModel)
    //inyectamos a este modulo el ViewModel del Detalle
    viewModelOf(::CharacterDetailViewModel)
}