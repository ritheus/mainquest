package com.mainquest.mainquest.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.History
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object TodoList : BottomNavItem("todo_list", "ToDos", Icons.AutoMirrored.Filled.List)
    object History : BottomNavItem("history", "Historie", Icons.Default.History)
}