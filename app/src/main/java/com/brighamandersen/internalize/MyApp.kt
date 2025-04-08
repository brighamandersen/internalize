package com.brighamandersen.internalize

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("details/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            DetailsScreen(navController, itemId)
        }
    }
}