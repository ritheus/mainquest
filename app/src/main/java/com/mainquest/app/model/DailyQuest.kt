package com.mainquest.app.model

data class DailyQuest(
    override val id: Int,
    override val title: String,
    override val reward: Int,
    override val description: String,
    override val isDone: Boolean = false
) : Quest {
    override fun toggleDone(): Quest = copy(isDone = !isDone)
}