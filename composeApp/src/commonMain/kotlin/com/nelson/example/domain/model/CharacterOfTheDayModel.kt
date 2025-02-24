package com.nelson.example.domain.model

import com.nelson.example.data.database.entity.CharacterOfTheDayEntity
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

data class CharacterOfTheDayModel(
    val characterModel: CharacterModel,
    val selectedDay:String
) {
    fun toEntity(): CharacterOfTheDayEntity {
        return CharacterOfTheDayEntity(
            id = characterModel.id,
            isAlive = characterModel.isAlive,
            image = characterModel.image,
            name = characterModel.name,
            selectedDay = selectedDay,
            species = characterModel.species,
            gender = characterModel.gender,
            origin = characterModel.origin,
            episode = Json.encodeToString(characterModel.episode)
        )
    }
}