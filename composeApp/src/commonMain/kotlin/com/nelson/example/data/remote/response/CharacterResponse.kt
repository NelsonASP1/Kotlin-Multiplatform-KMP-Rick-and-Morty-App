package com.nelson.example.data.remote.response

import com.nelson.example.domain.model.CharacterModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//Mapeamos los datos que vamos a recibir
@Serializable
class CharacterResponse (
    val id : Int,
    val status: String,
    // @SerialName("image")
    val image:String,
    val name:String,
    val species:String,
    val gender:String,
    val origin:OriginResponse,
    val episode: List<String>) {

    fun toDomain(): CharacterModel {
        return CharacterModel(
            id = id,
            image = image,
            //Esto convierte el String en Booleano y no tienen en cuenta las mayusculas
            isAlive = status.lowercase() == "alive",
            name = name,
            species = species,
            gender = gender,
            origin = origin.name,
            //Esto es para que me tome all antes del ultimo slash que seria el numero de episodios
            episode = episode.map { it.substringAfterLast("/") }

        )
    }
}