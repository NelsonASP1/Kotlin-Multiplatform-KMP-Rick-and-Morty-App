package com.nelson.example.ui.detail

import com.nelson.example.domain.model.CharacterModel
import com.nelson.example.domain.model.EpisodeModel

data class CharacterDetailState(
    val characterModel: CharacterModel,
    val episodes:List<EpisodeModel>? = null
)