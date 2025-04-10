package com.brighamandersen.internalize.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.Card
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brighamandersen.internalize.models.Passage

@Composable
fun HomeScreen(navController: NavController, passages: List<Passage>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Internalize") },
                backgroundColor = androidx.compose.material.MaterialTheme.colors.surface,
            )
        }
    ) {
            innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
//                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(passages) { passage -> // Loop directly through the items without an index
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