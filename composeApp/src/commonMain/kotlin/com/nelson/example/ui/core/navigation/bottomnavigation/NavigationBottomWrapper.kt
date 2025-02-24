package com.nelson.example.ui.core.navigation.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nelson.example.ui.core.navigation.Routes
import com.nelson.example.ui.home.characters.CharacterScreen
import com.nelson.example.ui.home.episodes.EpisodeScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun NavigationBottomWrapper(navController: NavHostController, mainNavController: NavHostController){

    NavHost(navController = navController, startDestination = Routes.Episodes.route){

        composable(route = Routes.Episodes.route){
            EpisodeScreen()
        }
        composable(route = Routes.Characters.route){
            //le enviamos una funcion lamda para que me navege al detalle
            CharacterScreen(
                navigateToDetail = { characterModel ->
                    //Esta variable me trae los datos del caracter
                    val encode: String = Json.encodeToString(characterModel)
                    mainNavController.navigate(Routes.CharacterDetail(encode))
                }
            )
        }
    }

}