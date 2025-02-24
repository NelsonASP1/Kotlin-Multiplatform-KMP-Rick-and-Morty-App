package com.nelson.example.ui.home.characters

import androidx.paging.PagingData
import com.nelson.example.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

//Toda la informacion del estado de la vista deberiamos tenerlo Aqui
data class CharacterState(
    val characterOFTheDay:CharacterModel? = null,

    val characters: Flow<PagingData<CharacterModel>> = emptyFlow()
)