package com.nelson.example.ui.core.navigation.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.nelson.example.ui.core.navigation.Routes

//Componenetes de una vista o fragment
sealed class BottomBarItem{
    abstract val route:String
    abstract val title:String
    abstract val icon:@Composable () -> Unit

    //fragment numero 1
    data class Episodes(
        override val route: String = Routes.Episodes.route,
        override val title: String = "Cap1",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Home, "")
        }
    ):BottomBarItem()

    //fragment numero 2
    data class Characters(
        override val route: String = Routes.Characters.route,
        override val title: String = "Cap2",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Person, "")
        }
    ):BottomBarItem()
}

//val episode = BottomBarItem.Episodes()