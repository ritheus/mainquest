package com.mainquest.app.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.mainquest.app.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DailyQuestsScreen(onNavigateToMainQuest: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text(stringResource(R.string.daily_quests_title), fontSize = 24.sp)
        Spacer(Modifier.height(16.dp))
        Button(onClick = onNavigateToMainQuest) {
            Text(stringResource(R.string.back_to_main_quest))
        }
    }
}