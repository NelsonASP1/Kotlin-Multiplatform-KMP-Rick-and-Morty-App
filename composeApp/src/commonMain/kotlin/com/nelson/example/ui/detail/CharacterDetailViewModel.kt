package com.nelson.example.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nelson.example.domain.Repository
import com.nelson.example.domain.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailViewModel(characterModel: CharacterModel, val repository: Repository):ViewModel() {

    private val _uiState = MutableStateFlow<CharacterDetailState>(CharacterDetailState(characterModel))
    val uiState: StateFlow<CharacterDetailState> = _uiState

    init {
        getEpisodeForCharacter(characterModel.episode)
    }

    private fun getEpisodeForCharacter(episode: List<String>) {

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getEpisodesForCharacter(episode)
            }
            _uiState.update { it.copy(episodes = result) }
        }
    }
}