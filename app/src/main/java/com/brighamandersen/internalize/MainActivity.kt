package com.brighamandersen.internalize

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.brighamandersen.internalize.data.PassageDatabase
import com.brighamandersen.internalize.ui.theme.Theme
import com.brighamandersen.internalize.viewmodels.PassageViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val db = Room.databaseBuilder(
            applicationContext,
            PassageDatabase::class.java,
            "passages_db"
        ).build()
        
        val passageViewModel = PassageViewModel(db.passageDao())
        
        setContent {
            Theme {
                App(passageViewModel)
            }
        }
    }
}
