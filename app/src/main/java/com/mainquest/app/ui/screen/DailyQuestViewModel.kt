package com.mainquest.app.ui.screen

import androidx.lifecycle.ViewModel
import com.mainquest.app.model.DailyQuest
import com.mainquest.app.model.Quest
import com.mainquest.app.model.SideQuest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DailyQuestViewModel : ViewModel() {

    private val _dailyQuests = MutableStateFlow<List<DailyQuest>>(emptyList())
    val dailyQuests: StateFlow<List<DailyQuest>> = _dailyQuests

    private var nextId = 0

    fun addDailyQuest() {
        val newQuest = DailyQuest(
            id = nextId++,
            title = "New Daily Quest",
            reward = 1,
            description = "asdf"
        )
        _dailyQuests.value = _dailyQuests.value + newQuest
    }

    fun updateQuest(updated: Quest) {
        _dailyQuests.value = _dailyQuests.value.map {
            (if (it.id == updated.id) updated as DailyQuest else it)
        }
    }
}