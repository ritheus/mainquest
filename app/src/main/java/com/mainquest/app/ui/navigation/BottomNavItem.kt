package com.mainquest.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Article
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material.icons.outlined.MilitaryTech
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object MainQuest : BottomNavItem("main_quest", "Main Quest", Icons.AutoMirrored.Outlined.Article)
    object SideQuests : BottomNavItem("side_quests", "Side Quests", Icons.Outlined.EmojiEvents)
    object DailyQuests : BottomNavItem("daily_quests", "Daily Quests", Icons.Outlined.MilitaryTech)
    object Character : BottomNavItem("character", "Character", Icons.Outlined.Person)
}