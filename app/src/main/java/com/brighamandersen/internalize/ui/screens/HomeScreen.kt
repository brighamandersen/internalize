package com.brighamandersen.internalize.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brighamandersen.internalize.models.Passage
import com.brighamandersen.internalize.viewmodels.PassageViewModel
import com.brighamandersen.internalize.utils.NavRoutes

@Composable
fun HomeScreen(navController: NavController, passageViewModel: PassageViewModel) {
    val passages = passageViewModel.passages

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Internalize",
                ) },
                backgroundColor = MaterialTheme.colors.surface,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoutes.CREATE_PASSAGE)
                },
                backgroundColor = MaterialTheme.colors.primary,
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Create new passage")
            }
        },
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
                items(
                    items = passages,
                    key = { passage -> passage.id }
                ) { passage ->
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
                    navController.navigate("${NavRoutes.DETAILS}/${passage.id}")
                }
            )
            .fillMaxWidth()
            .padding(16.dp)
    )
}