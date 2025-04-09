package com.brighamandersen.internalize.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    val passages = listOf("Apple", "Banana", "Cherry", "Date", "Elderberry")

//    Column(modifier = Modifier
//        .fillMaxSize()
//        .padding(16.dp)) {
//        Text("Home Screen")
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = {
//            navController.navigate("details/123")
//        }) {
//            Text("Go to Details")
//        }
//    }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Internalize", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(passages) { passage -> // Loop directly through the items without an index
                ListItem(
                    text = passage,
                    id = passage.lowercase(),  // You can use the `passage` directly here
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun ListItem(text: String, id: String, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
               navController.navigate("details/$id")
            },
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}