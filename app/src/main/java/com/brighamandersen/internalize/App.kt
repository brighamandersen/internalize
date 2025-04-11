package com.brighamandersen.internalize

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.brighamandersen.internalize.viewmodels.PassageViewModel
import com.brighamandersen.internalize.ui.CreatePassageScreen
import com.brighamandersen.internalize.ui.DetailsScreen
import com.brighamandersen.internalize.ui.EditPassageScreen
import com.brighamandersen.internalize.ui.HomeScreen
import com.brighamandersen.internalize.utils.NavigationRoutes

@Composable
fun App() {
    val navController = rememberNavController()
    val passageViewModel: PassageViewModel = viewModel()

    NavHost(navController, startDestination = NavigationRoutes.HOME) {
        composable(NavigationRoutes.HOME) { HomeScreen(navController, passageViewModel) }
        composable(NavigationRoutes.CREATE_PASSAGE) { CreatePassageScreen(navController, passageViewModel) }
        composable("${NavigationRoutes.DETAILS}/{passageId}") { backStackEntry ->
            val passageId = backStackEntry.arguments?.getString("passageId")
            val passage = passageViewModel.getPassageById(passageId)
            DetailsScreen(navController, passage)
        }
        composable("${NavigationRoutes.EDIT_PASSAGE}/{passageId}") { backStackEntry ->
            val passageId = backStackEntry.arguments?.getString("passageId")
            val passage = passageViewModel.getPassageById(passageId)
            EditPassageScreen(navController, passage)
        }
    }
}