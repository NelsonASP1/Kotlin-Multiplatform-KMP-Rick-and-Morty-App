package com.nelson.example.ui.home.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nelson.example.domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

//El viewModel llama al caso de Uso
class EpisodesViewModel(private val repository: Repository): ViewModel() {

    private val _state = MutableStateFlow<EpisodeState>(EpisodeState())
    val state: StateFlow<EpisodeState> = _state

    //Enviar los episodios
    init {
        _state.update { state -> state.copy(
            characters = repository.getAllEpisodes().cachedIn(viewModelScope)) }
    }

    //Enviar la URL
    fun onPlaySelected(url: String) {

        _state.update { state ->state.copy(playVideo = url) }
    }

    //Cerrar el video
    fun onCloseVideo() {
        _state.update { state -> state.copy(playVideo = "") }
    }
}