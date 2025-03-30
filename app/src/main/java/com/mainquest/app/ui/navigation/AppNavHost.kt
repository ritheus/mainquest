package com.mainquest.app.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mainquest.app.ui.screen.DailyQuestsScreen
import com.mainquest.app.ui.screen.HistoryScreen
import com.mainquest.app.ui.screen.MainQuestScreen
import com.mainquest.app.ui.screen.SideQuestsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    val bottomNavItems = listOf(
        BottomNavItem.MainQuest,
        BottomNavItem.SideQuests,
        BottomNavItem.DailyQuests,
        BottomNavItem.History
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, items = bottomNavItems)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.MainQuest.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.MainQuest.route) {
                MainQuestScreen()
            }
            composable(BottomNavItem.SideQuests.route) {
                SideQuestsScreen(
                    onNavigateToMainQuest = { navController.navigate(BottomNavItem.MainQuest.route) }
                )
            }
            composable(BottomNavItem.DailyQuests.route) {
                DailyQuestsScreen(
                    onNavigateToMainQuest = { navController.navigate(BottomNavItem.MainQuest.route) }
                )
            }
            composable(BottomNavItem.History.route) {
                HistoryScreen(
                    onNavigateToMainQuest = { navController.navigate(BottomNavItem.MainQuest.route) }
                )
            }
        }
    }
}