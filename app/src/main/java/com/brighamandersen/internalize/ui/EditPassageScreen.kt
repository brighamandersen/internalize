package com.brighamandersen.internalize.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brighamandersen.internalize.models.Passage

@Composable
fun EditPassageScreen(navController: NavController, passage: Passage?) {
    if (passage == null) {
        PassageNotFound()
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Passage") },
                navigationIcon = {
                    BackButton(navController = navController)
                },
                backgroundColor = MaterialTheme.colors.surface,
            )
        }
    ) {
        innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(text = "FIXME EDIT")
        }
    }
}