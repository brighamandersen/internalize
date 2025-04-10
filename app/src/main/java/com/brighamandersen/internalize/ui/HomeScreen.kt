package com.brighamandersen.internalize.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brighamandersen.internalize.models.Passage

@Composable
fun HomeScreen(navController: NavController, passages: List<Passage>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Internalize",
                ) },
                backgroundColor = MaterialTheme.colors.surface,
            )
        }
    ) {
            innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(passages) { passage ->
                    ListItem(
                        navController = navController,
                        passage = passage
                    )
                }
            }
        }
    }
}

@Composable
fun ListItem(navController: NavController, passage: Passage) {
    Text(
        text = passage.title,
        modifier = Modifier
            .clickable(
                onClick = {
                    navController.navigate("details/${passage.id}")
                }
            )
            .fillMaxWidth()
            .padding(16.dp)
    )
}