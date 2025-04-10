package com.brighamandersen.internalize.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.brighamandersen.internalize.models.Passage

@Composable
fun App() {
    val navController = rememberNavController()
    val passages = listOf(
        Passage("1", "Preamble", "We the people of the United States"),
        Passage("2", "Succeed Breathe", "When you wish to succeed as bad as you want to breathe, then you'll be successful."),
        Passage("3", "Shots Missed", "You miss 100% of the shots you don't take."),
    );

    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController, passages) }
        composable("details/{passageId}") { backStackEntry ->
            val passageId = backStackEntry.arguments?.getString("passageId")
            val passage = passages.find { it.id == passageId }
            DetailsScreen(navController, passage)
        }
    }
}