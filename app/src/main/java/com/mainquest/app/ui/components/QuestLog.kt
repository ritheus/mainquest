package com.mainquest.app.ui.components

import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mainquest.app.model.Quest

@Composable
fun QuestLog(
    quests: List<Quest>,
    onToggleDone: (Quest) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(quests) { quest ->
            QuestCard(
                title = quest.title,
                reward = quest.reward,
                description = quest.description,
                isDone = false,
                onToggleDone = { isChecked ->
                    onToggleDone(quest.toggleDone())
                }
            )
        }
    }
}