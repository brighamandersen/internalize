package com.brighamandersen.internalize.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brighamandersen.internalize.ui.components.BackButton
import com.brighamandersen.internalize.ui.components.PassageNotFound
import com.brighamandersen.internalize.utils.NavRoutes
import com.brighamandersen.internalize.viewmodels.PassageViewModel

enum class VisibilityLevel {
    NONE, SOME, ALL
}

@Composable
fun DetailsScreen(navController: NavController, passageViewModel: PassageViewModel, passageId: String?) {
    val context = LocalContext.current
    var visibilityLevel by remember { mutableStateOf(VisibilityLevel.ALL) }

    // Cache passage so it doesn't flicker screen when it gets deleted.
    val passage = remember(passageId) {
        passageViewModel.getPassageById(passageId)
    }
    var isOverflowMenuExpanded by remember { mutableStateOf(false) }

    if (passageId == null || passage == null) {
        PassageNotFound()
        return
    }

    val processedText = remember(passage.body, visibilityLevel) {
        when (visibilityLevel) {
            VisibilityLevel.NONE -> passage.body.map { if (it == ' ') ' ' else '_' }.joinToString("")
            VisibilityLevel.SOME -> passage.body.split(" ").map { word ->
                if (word.isEmpty()) ""
                else word.first() + "_".repeat(word.length - 1)
            }.joinToString(" ")
            VisibilityLevel.ALL -> passage.body
        }
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
                            navController.navigate("${NavRoutes.EDIT_PASSAGE}/${passage.id}")
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = "Edit",
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text("Edit")
                        }
                        DropdownMenuItem(onClick = {
                            isOverflowMenuExpanded = false
                            passageViewModel.deletePassage(passage.id)
                            Toast.makeText(context, "Passage deleted", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = "Delete",
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text("Delete")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = processedText,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Button(
                    onClick = { visibilityLevel = VisibilityLevel.NONE },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (visibilityLevel == VisibilityLevel.NONE) 
                            MaterialTheme.colors.primary 
                        else 
                            MaterialTheme.colors.surface
                    )
                ) {
                    Text(
                        "None",
                        color = if (visibilityLevel == VisibilityLevel.NONE) 
                            MaterialTheme.colors.onPrimary 
                        else 
                            MaterialTheme.colors.onSurface
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { visibilityLevel = VisibilityLevel.SOME },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (visibilityLevel == VisibilityLevel.SOME) 
                            MaterialTheme.colors.primary 
                        else 
                            MaterialTheme.colors.surface
                    )
                ) {
                    Text(
                        "Some",
                        color = if (visibilityLevel == VisibilityLevel.SOME) 
                            MaterialTheme.colors.onPrimary 
                        else 
                            MaterialTheme.colors.onSurface
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { visibilityLevel = VisibilityLevel.ALL },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (visibilityLevel == VisibilityLevel.ALL) 
                            MaterialTheme.colors.primary 
                        else 
                            MaterialTheme.colors.surface
                    )
                ) {
                    Text(
                        "All",
                        color = if (visibilityLevel == VisibilityLevel.ALL) 
                            MaterialTheme.colors.onPrimary 
                        else 
                            MaterialTheme.colors.onSurface
                    )
                }
            }
        }
    }
}
