package com.mainquest.app.model

sealed interface Quest {
    val id: Int
    val title: String
    val reward: Int
    val description: String
    val isDone: Boolean
    fun toggleDone(): Quest
}