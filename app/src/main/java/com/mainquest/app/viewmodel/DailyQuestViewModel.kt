package com.mainquest.app.viewmodel

import androidx.lifecycle.ViewModel
import com.mainquest.app.model.DailyQuest
import com.mainquest.app.model.Quest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DailyQuestViewModel : ViewModel() {

    private val _dailyQuests = MutableStateFlow<List<DailyQuest>>(emptyList())
    val dailyQuests: StateFlow<List<DailyQuest>> = _dailyQuests

    private var nextId = 0

    fun addDailyQuest(title: String, description: String, reward: Int) {
        val newQuest = DailyQuest(
            id = nextId++,
            title = title,
            reward = reward,
            description = description
        )
        _dailyQuests.value = _dailyQuests.value + newQuest
    }

    fun updateQuest(updated: Quest) {
        _dailyQuests.value = _dailyQuests.value.map {
            (if (it.id == updated.id) updated as DailyQuest else it)
        }
    }
}