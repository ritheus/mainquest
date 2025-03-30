package com.mainquest.mainquest.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TodoListScreen(onNavigateToHistory: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("📝 ToDo Liste", fontSize = 24.sp)
        Spacer(Modifier.height(16.dp))
        Button(onClick = onNavigateToHistory) {
            Text("Zur Historie")
        }
    }
}