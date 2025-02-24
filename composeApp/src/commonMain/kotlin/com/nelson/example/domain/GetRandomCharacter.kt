package com.nelson.example.domain

import com.nelson.example.data.database.entity.CharacterOfTheDayEntity
import com.nelson.example.domain.model.CharacterModel
import com.nelson.example.domain.model.CharacterOfTheDayModel
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

//Este caso de uso que esta en el dominio no puede acceder a la base de datos directamente
// tienen que hacerlo el repositorio
class GetRandomCharacter(private val repository: Repository) {

    suspend operator fun invoke():CharacterModel{

        val characterOFTheDay: CharacterOfTheDayModel? = repository.getCharacterDB()
        val selectedDay = getCurrentDayOFTheYear()

        return if (characterOFTheDay != null && characterOFTheDay.selectedDay == getCurrentDayOFTheYear() ){
            characterOFTheDay.characterModel
        }else{
            val result = generateRandomCharacter()
            //me salva el caracter del dia en la base de datos
            repository.saveCharacterDB(CharacterOfTheDayModel(characterModel = result, selectedDay))
            result
        }
    }

    private suspend fun generateRandomCharacter():CharacterModel{
        //me devolvera un id de un personaje Random
        val random: Int = (1..826).random()
        return repository.getSingleCharacter(random.toString())
    }

    private fun getCurrentDayOFTheYear():String{
        val fecha: Instant = Clock.System.now()
        //ESta es la fecha actual del dispositivo
        val fechaActual: LocalDateTime = fecha.toLocalDateTime(TimeZone.currentSystemDefault())

        return "${fechaActual.dayOfYear}${fechaActual.year}"
    }
}