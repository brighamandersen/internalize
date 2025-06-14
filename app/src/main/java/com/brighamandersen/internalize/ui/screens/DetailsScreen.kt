package com.brighamandersen.internalize.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.brighamandersen.internalize.ui.components.BackButton
import com.brighamandersen.internalize.ui.components.PassageNotFound
import com.brighamandersen.internalize.ui.theme.PureBlack
import com.brighamandersen.internalize.utils.NavRoutes
import com.brighamandersen.internalize.viewmodels.PassageViewModel
import java.util.regex.Pattern

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
            VisibilityLevel.NONE -> convertTextToBlanks(passage.body)
            VisibilityLevel.SOME -> convertTextToBlanksBesidesFirstLetters(passage.body)
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 72.dp) // Add padding for the button group
            ) {
                Text(
                    text = processedText,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                )
            }
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        border = BorderStroke(2.dp, MaterialTheme.colors.primary),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Button(
                    onClick = { visibilityLevel = VisibilityLevel.NONE },
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (visibilityLevel == VisibilityLevel.NONE) 
                            MaterialTheme.colors.primary 
                        else 
                            Color.Transparent
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp
                    ),
                    shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                ) {
                    Text(
                        "None",
                        color = if (visibilityLevel == VisibilityLevel.NONE) 
                            MaterialTheme.colors.onPrimary 
                        else 
                            MaterialTheme.colors.onSurface
                    )
                }
                Divider(
                    modifier = Modifier
                        .width(2.dp)
                        .height(40.dp),
                    color = MaterialTheme.colors.primary
                )
                Button(
                    onClick = { visibilityLevel = VisibilityLevel.SOME },
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (visibilityLevel == VisibilityLevel.SOME) 
                            MaterialTheme.colors.primary 
                        else 
                            Color.Transparent
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp
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
                Divider(
                    modifier = Modifier
                        .width(2.dp)
                        .height(40.dp),
                    color = MaterialTheme.colors.primary
                )
                Button(
                    onClick = { visibilityLevel = VisibilityLevel.ALL },
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (visibilityLevel == VisibilityLevel.ALL) 
                            MaterialTheme.colors.primary 
                        else 
                            Color.Transparent
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp
                    ),
                    shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
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

private fun convertTextToBlanks(text: String): String {
    return text.replace(Regex("[^\\s\\p{P}]"), "_")
}

private fun convertTextToBlanksBesidesFirstLetters(text: String): String {
    val pattern = Pattern.compile("\\b(\\d+|\\w)(\\w*)\\b")
    val matcher = pattern.matcher(text)
    val result = StringBuilder()
    var lastEnd = 0
    
    while (matcher.find()) {
        // Add any text between matches (spaces, newlines, etc.)
        result.append(text.substring(lastEnd, matcher.start()))
        
        val firstGroup = matcher.group(1)
        val secondGroup = matcher.group(2)
        
        if (firstGroup != null && secondGroup != null) {
            if (firstGroup.matches(Regex("^\\d+$"))) {
                // If the word starts with a number, leave it unchanged
                result.append(matcher.group(0))
            } else {
                // Keep first letter, replace rest with underscores
                result.append(firstGroup)
                result.append(secondGroup.replace(Regex("\\w"), "_"))
            }
        }
        
        lastEnd = matcher.end()
    }
    
    // Add any remaining text after the last match
    if (lastEnd < text.length) {
        result.append(text.substring(lastEnd))
    }
    
    return result.toString()
}
