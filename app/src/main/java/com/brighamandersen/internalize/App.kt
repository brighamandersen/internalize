package com.brighamandersen.internalize

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.brighamandersen.internalize.viewmodels.PassageViewModel
import com.brighamandersen.internalize.ui.CreatePassageScreen
import com.brighamandersen.internalize.ui.DetailsScreen
import com.brighamandersen.internalize.ui.EditPassageScreen
import com.brighamandersen.internalize.ui.HomeScreen
import com.brighamandersen.internalize.utils.NavRoutes

@Composable
fun App() {
    val navController = rememberNavController()
    val passageViewModel: PassageViewModel = viewModel()

    NavHost(navController, startDestination = NavRoutes.HOME) {
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