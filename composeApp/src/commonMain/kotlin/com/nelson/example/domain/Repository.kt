package com.nelson.example.domain

import androidx.paging.PagingData
import com.nelson.example.data.database.entity.CharacterOfTheDayEntity
import com.nelson.example.domain.model.CharacterModel
import com.nelson.example.domain.model.CharacterOfTheDayModel
import com.nelson.example.domain.model.EpisodeModel
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getSingleCharacter(id:String): CharacterModel

    //En esta ocacion no sera una funion suspendida ya que no es una corrutina y eso sucede
    // por que la libreria de paginacion nos regresa un Flow
    fun getAllCharacters(): Flow<PagingData<CharacterModel>>

    //Inyectamos esta funcion de la busqueda del caracter del mes en la base de datos
    suspend fun getCharacterDB(): CharacterOfTheDayModel?

    //Me guarda el personaje en base de datos
    suspend fun saveCharacterDB(characterOfTheDayModel:CharacterOfTheDayModel)

    //Obtengo todos los episodios
    fun getAllEpisodes(): Flow<PagingData<EpisodeModel>>

    //Obtengo los episodios de cara caracter
    suspend fun getEpisodesForCharacter(episode: List<String>):List<EpisodeModel>
}