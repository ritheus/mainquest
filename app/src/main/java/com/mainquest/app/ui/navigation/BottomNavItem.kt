package com.mainquest.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.History
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object MainQuest : BottomNavItem("main_quest", "MainQuest", Icons.AutoMirrored.Filled.List)
    object SideQuests : BottomNavItem("side_quests", "SideQuests", Icons.AutoMirrored.Filled.List)
    object DailyQuests : BottomNavItem("daily_quests", "DailyQuests", Icons.AutoMirrored.Filled.List)
    object History : BottomNavItem("history", "Historie", Icons.Default.History)
}