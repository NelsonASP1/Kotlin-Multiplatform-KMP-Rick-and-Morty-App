package com.nelson.example.ui.core.navigation

import com.nelson.example.domain.model.CharacterModel
import kotlinx.serialization.Serializable

sealed class Routes(val route:String){
    //home
    data object Home:Routes("home")

    //BottomNav
    data object Episodes:Routes("episodes")
    data object Characters:Routes("characters")

    //Detalle (la serializacion es convertir un objeto grande en un string, JSON etc. para poderlo manipular)
    @Serializable
    data class CharacterDetail(val characterModel: String)
}
