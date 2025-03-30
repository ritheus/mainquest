package com.mainquest.app.ui.screen

import androidx.lifecycle.ViewModel
import com.mainquest.app.model.Quest
import com.mainquest.app.model.SideQuest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SideQuestViewModel : ViewModel() {

    private val _sideQuests = MutableStateFlow<List<SideQuest>>(emptyList())
    val sideQuests: StateFlow<List<SideQuest>> = _sideQuests

    private var nextId = 0

    fun addSideQuest() {
        val newQuest = SideQuest(
            id = nextId++,
            title = "New Quest #$nextId",
            reward = 3,
            description = ""
        )
        _sideQuests.value = _sideQuests.value + newQuest
    }

    fun updateQuest(updated: Quest) {
        _sideQuests.value = _sideQuests.value.map {
            if (it.id == updated.id) updated as SideQuest else it
        }
    }
}