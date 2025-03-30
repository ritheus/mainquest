package com.mainquest.mainquest.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mainquest.mainquest.ui.screen.HistoryScreen
import com.mainquest.mainquest.ui.screen.TodoListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoNavHost() {
    val navController = rememberNavController()

    val bottomNavItems = listOf(
        BottomNavItem.TodoList,
        BottomNavItem.History
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, items = bottomNavItems)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.TodoList.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.TodoList.route) {
                TodoListScreen(
                    onNavigateToHistory = { navController.navigate(BottomNavItem.History.route) }
                )
            }
            composable(BottomNavItem.History.route) {
                HistoryScreen(
                    onNavigateBack = { navController.navigate(BottomNavItem.TodoList.route) }
                )
            }
        }
    }
}