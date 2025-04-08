package com.brighamandersen.internalize

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.brighamandersen.internalize.ui.theme.InternalizeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InternalizeTheme {
                MyApp()
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(24.dp),
//                        verticalArrangement = Arrangement.Center
//                    ) {
//                        Text("Hello from MainActivity 👋")
//                    }
//                }
            }
        }
    }
}

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

@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Home Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("details/123")
        }) {
            Text("Go to Details")
        }
    }
}

@Composable
fun DetailsScreen(navController: NavController, itemId: String?) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Details of item: $itemId")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("home")
        }) {
            Text("Return Home")
        }
    }
}