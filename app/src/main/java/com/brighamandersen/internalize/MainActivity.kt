package com.brighamandersen.internalize

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.brighamandersen.internalize.ui.theme.Theme
import com.brighamandersen.internalize.viewmodels.PassageViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                val passageViewModel: PassageViewModel = viewModel()
                App(passageViewModel)
            }
        }
    }
}
