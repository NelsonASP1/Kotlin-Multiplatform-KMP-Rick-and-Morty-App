package com.nelson.example.domain.model

import com.nelson.example.data.remote.response.OriginResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterModel (
    val id : Int,
    val isAlive: Boolean,
    val image:String,
    val name: String,
    val species: String,
    val gender:String,
    val origin: String,
    val episode: List<String>)