package com.brighamandersen.internalize.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.brighamandersen.internalize.ui.DetailsScreen
import com.brighamandersen.internalize.ui.HomeScreen

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("details/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            DetailsScreen(navController, itemId)
        }
    }
}