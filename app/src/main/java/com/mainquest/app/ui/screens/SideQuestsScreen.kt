package com.mainquest.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import com.mainquest.app.R
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mainquest.app.ui.components.QuestLog
import com.mainquest.app.viewmodel.SideQuestViewModel


@Composable
fun SideQuestsScreen(
    onNavigateToMainQuest: () -> Unit,
    viewModel: SideQuestViewModel = viewModel()
) {
    val sideQuests = viewModel.sideQuests.collectAsState().value

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { viewModel.addSideQuest() },
                icon = { Icon(Icons.Default.Add, contentDescription = null) },
                text = { Text(stringResource(R.string.add_sidequest)) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(stringResource(R.string.side_quests_title), fontSize = 24.sp)
            Spacer(Modifier.height(16.dp))

            QuestLog(
                quests = sideQuests,
                onToggleDone = { updated ->
                    viewModel.updateQuest(updated)
                }
            )
        }
    }
}