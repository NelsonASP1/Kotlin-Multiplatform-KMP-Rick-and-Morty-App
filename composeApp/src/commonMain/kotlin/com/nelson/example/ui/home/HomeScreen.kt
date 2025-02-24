package com.nelson.example.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nelson.example.ui.core.BackgroundSecondaryColor
import com.nelson.example.ui.core.BackgroundTerciaryColor
import com.nelson.example.ui.core.DefaultTextColor
import com.nelson.example.ui.core.Green
import com.nelson.example.ui.core.navigation.bottomnavigation.BottomBarItem
import com.nelson.example.ui.core.navigation.bottomnavigation.BottomBarItem.*
import com.nelson.example.ui.core.navigation.bottomnavigation.NavigationBottomWrapper

@Composable
fun HomeScreen(mainNavController: NavHostController) {
    //lista con las diferentes pantallas y rutas
    val items = listOf(Episodes(), Characters())
    //para controlar el swich entre las pantallas
    val navControler = rememberNavController()

    //funcion para organizar los botones del menu en la parte de abajo
    Scaffold (bottomBar = { BottomNavigation(items, navControler) }){ pading ->
        Box(modifier = Modifier.padding(pading)){
            NavigationBottomWrapper(navControler, mainNavController)
        }
    }
}

@Composable
fun BottomNavigation(items: List<BottomBarItem>, navControler: NavHostController) {
    val navBackStackEntry by navControler.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar(containerColor = BackgroundSecondaryColor, contentColor = Green) {
        items.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Green,
                    selectedIconColor = BackgroundTerciaryColor,
                    unselectedIconColor = Green
                ),
                icon = item.icon,
                label = { Text(item.title, color = DefaultTextColor) },
                alwaysShowLabel = false,
                onClick = {
                    //con esto vamos a la ruta que queramos
                    navControler.navigate(route = item.route){
                        //con esto reutilizamos la vista para que no se generen muchas de las mismas vistas
                        navControler.graph.startDestinationRoute?.let { route ->
                            popUpTo(route){
                                saveState = true
                            }
                        }
                        //con esto evitamos que si oprimimos mucho el mismo boton se creen muchas vista
                        launchSingleTop = true
                        restoreState = true
                    }},
                selected = currentDestination?.hierarchy?.any {it.route == item.route} == true
            )

        }

    }
}
