package com.brighamandersen.internalize.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brighamandersen.internalize.utils.NavigationRoutes
import com.brighamandersen.internalize.viewmodels.PassageViewModel

@Composable
fun DetailsScreen(navController: NavController, passageViewModel: PassageViewModel, passageId: String?) {
    // Cache passage so it doesn't flicker screen when it gets deleted.
    val passage = remember(passageId) {
        passageViewModel.getPassageById(passageId)
    }
    var isOverflowMenuExpanded by remember { mutableStateOf(false) }

    if (passageId == null || passage == null) {
        PassageNotFound()
        return
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(passage.title) },
                navigationIcon = {
                    BackButton(navController = navController)
                },
                backgroundColor = MaterialTheme.colors.surface,
                actions = {
                    IconButton(onClick = { isOverflowMenuExpanded = !isOverflowMenuExpanded }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More options")
                    }
                    DropdownMenu(
                        expanded = isOverflowMenuExpanded,
                        onDismissRequest = { isOverflowMenuExpanded = false }
                    ) {
                        DropdownMenuItem(onClick = {
                            isOverflowMenuExpanded = false
                            navController.navigate("${NavigationRoutes.EDIT_PASSAGE}/${passage.id}")
                        }) {
                            Text("Edit")
                        }
                        DropdownMenuItem(onClick = {
                            isOverflowMenuExpanded = false
                            passageViewModel.deletePassage(passage.id)
                            navController.popBackStack()
                        }) {
                            Text("Delete")
                        }
                    }
                }
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
            Text(text = passage.body)
        }
    }
}
