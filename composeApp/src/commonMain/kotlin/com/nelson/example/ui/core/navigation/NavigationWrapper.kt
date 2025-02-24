package com.nelson.example.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.nelson.example.domain.model.CharacterModel
import com.nelson.example.ui.detail.CharacterDetailScreen
import com.nelson.example.ui.home.HomeScreen
import kotlinx.serialization.json.Json

@Composable
fun NavigationWrapper(){
    val mainNavController = rememberNavController()
    //funcion Lamda
    NavHost(navController = mainNavController, startDestination = Routes.Home.route){
        composable(route = Routes.Home.route){
            HomeScreen(mainNavController)
        }

        composable<Routes.CharacterDetail>{ navBackStackEntry ->
            val characterDetailEncoding: Routes.CharacterDetail = navBackStackEntry.toRoute<Routes.CharacterDetail>()
            val characterModel = Json.decodeFromString<CharacterModel>(characterDetailEncoding.characterModel)
            CharacterDetailScreen(characterModel)
        }
    }
}