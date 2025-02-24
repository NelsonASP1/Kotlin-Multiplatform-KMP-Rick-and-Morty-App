package com.nelson.example.data

import com.nelson.example.data.remote.response.CharacterResponse
import com.nelson.example.data.remote.response.CharactersWrapperResponse
import com.nelson.example.data.remote.response.EpisodeResponse
import com.nelson.example.data.remote.response.EpisodesWrapperResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

//Ya inyectamos previamente el HttpClient por eso lo podemos utilizar aqui
class ApiService(private val client: HttpClient) {

    //Obtenemos un unico item
    suspend fun getSingleCharacter(id:String): CharacterResponse{
        return client.get("api/character/$id").body()

    }

    //Obtenemos todos los Caracteres
    suspend fun getAllCharacters(page: Int): CharactersWrapperResponse {
        return client.get("api/character/"){
            parameter("page", page)
        }.body()
    }

    //Me regresa todos los episodios
    suspend fun getAllEpisodes(page: Int): EpisodesWrapperResponse {
        return client.get("api/episode"){
            parameter("page",page)
        }.body()
    }

    suspend fun getEpisodes(episodes: String):List<EpisodeResponse> {
        return client.get("/api/episode/$episodes").body()
    }

    //Es la misma llamada solo que la respuesta solo sera un episodio ya que el API tiene ese error
    suspend fun getSingleEpisode(episodeId: String):EpisodeResponse {

        return client.get("/api/episode/$episodeId").body()
    }
}