package com.nelson.example.ui.home.episodes

import androidx.paging.PagingData
import com.nelson.example.domain.model.EpisodeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class EpisodeState(
    val characters: Flow<PagingData<EpisodeModel>> = emptyFlow(),
    //Por medio del estado se averiguara cuando el usuario aga click en un objeto y reprodusca el video
    val playVideo: String = ""
)