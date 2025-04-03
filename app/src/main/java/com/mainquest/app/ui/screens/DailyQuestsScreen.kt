package com.mainquest.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mainquest.app.R
import com.mainquest.app.ui.components.AddQuestDialog
import com.mainquest.app.ui.components.QuestLog
import com.mainquest.app.viewmodel.DailyQuestViewModel

@Composable
fun DailyQuestsScreen(
    onNavigateToMainQuest: () -> Unit,
    viewModel: DailyQuestViewModel = viewModel()
) {
    val dailyQuests = viewModel.dailyQuests.collectAsState().value
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AddQuestDialog(
            onSave = { title, description, xp ->
                viewModel.addDailyQuest(title, description, xp)
                showDialog = false
            },
            onCancel = { showDialog = false }
        )
    }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { showDialog = true },
                icon = { Icon(Icons.Default.Add, contentDescription = null) },
                text = { Text(stringResource(R.string.add_dailyquest)) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(stringResource(R.string.daily_quests_title), fontSize = 24.sp)
            Spacer(Modifier.height(16.dp))

            QuestLog(
                quests = dailyQuests,
                onToggleDone = { updated ->
                    viewModel.updateQuest(updated)
                }
            )
        }
    }
}