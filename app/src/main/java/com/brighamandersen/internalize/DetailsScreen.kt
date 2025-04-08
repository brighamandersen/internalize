package com.brighamandersen.internalize

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

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