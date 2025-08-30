package com.brighamandersen.internalize

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import com.brighamandersen.internalize.viewmodels.PassageViewModel
import com.brighamandersen.internalize.ui.screens.CreatePassageScreen
import com.brighamandersen.internalize.ui.screens.DetailsScreen
import com.brighamandersen.internalize.ui.screens.EditPassageScreen
import com.brighamandersen.internalize.ui.screens.HomeScreen
import com.brighamandersen.internalize.utils.NavRoutes

@Composable
fun App(passageViewModel: PassageViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController, 
        startDestination = NavRoutes.HOME,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable(NavRoutes.HOME) { HomeScreen(navController, passageViewModel) }
        composable(NavRoutes.CREATE_PASSAGE) { CreatePassageScreen(navController, passageViewModel) }
        composable("${NavRoutes.DETAILS}/{passageId}") { backStackEntry ->
            val passageId = backStackEntry.arguments?.getString("passageId")
            DetailsScreen(navController, passageViewModel, passageId)
        }
        composable("${NavRoutes.EDIT_PASSAGE}/{passageId}") { backStackEntry ->
            val passageId = backStackEntry.arguments?.getString("passageId")
            EditPassageScreen(navController, passageViewModel, passageId)
        }
    }
}