package com.brighamandersen.internalize.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brighamandersen.internalize.ui.components.BackButton
import com.brighamandersen.internalize.ui.components.PassageNotFound
import com.brighamandersen.internalize.viewmodels.PassageViewModel

@Composable
fun EditPassageScreen(
    navController: NavController,
    passageViewModel: PassageViewModel,
    passageId: String?
) {
    val passage = passageViewModel.getPassageById(passageId)
    if (passageId == null || passage == null) {
        PassageNotFound()
        return
    }

    var title by remember { mutableStateOf(passage.title) }
    var body by remember { mutableStateOf(passage.body) }

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
                .padding(top = 0.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                placeholder = { Text("New title") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = body,
                onValueChange = { body = it },
                label = { Text("Body") },
                placeholder = { Text("New body") },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Button(
                onClick = {
                    if (title.isNotBlank() && body.isNotBlank()) {
                        passageViewModel.editPassage(passageId, title, body)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Save")
            }
        }
    }
}
