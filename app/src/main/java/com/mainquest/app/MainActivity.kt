package com.mainquest.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mainquest.app.ui.navigation.AppNavHost
import com.mainquest.app.ui.theme.MainQuestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainQuestTheme {
                AppNavHost()
            }
        }
    }
}