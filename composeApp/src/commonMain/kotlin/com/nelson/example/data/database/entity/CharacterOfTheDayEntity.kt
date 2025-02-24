package com.nelson.example.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nelson.example.domain.model.CharacterModel
import com.nelson.example.domain.model.CharacterOfTheDayModel
import kotlinx.serialization.json.Json

@Entity(tableName = "CharacterOfTheDay")
data class CharacterOfTheDayEntity(
    @PrimaryKey
    val id : Int,
    val isAlive: Boolean,
    val image:String,
    val name: String,
    val selectedDay:String,
    val species:String,
    val gender:String,
    val origin:String,
    val episode:String
) {
    fun toDomain(): CharacterOfTheDayModel? {
       return CharacterOfTheDayModel(
           characterModel = CharacterModel(
               id = id,
               isAlive = isAlive,
               image = image,
               name = name,
               species = species,
               gender = gender,
               origin = origin,
               //Esto lo hacemos ya que en la base de datos no podemos almacenar listas con tantos
               // objetos, ya que en esta version de Room no funciona de la manera normal
               episode = Json.decodeFromString<List<String>>(episode)
           ),
           selectedDay = selectedDay
        )
    }
}

//Si no hay ID se utiliza @PrimaryKey(autogenerate = true)