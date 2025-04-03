package com.mainquest.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mainquest.app.R
import com.mainquest.app.viewmodel.CharacterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterScreen(
    onNavigateToMainQuest: () -> Unit,
    viewModel: CharacterViewModel = viewModel()
) {
    val xp by viewModel.currentXp.collectAsState()
    val level by viewModel.level.collectAsState()
    val xpToNext by viewModel.xpToNextLevel.collectAsState()
    val progress by viewModel.xpProgress.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Character") },
                navigationIcon = {
                    IconButton(onClick = onNavigateToMainQuest) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.avatar_placeholder_male), // füge später dein Bild hinzu
                contentDescription = "Character Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Text("Name: The Focused One", fontSize = 20.sp)
            Text("Level: $level", fontSize = 18.sp)

            Column {
                Text("XP: $xp / $xpToNext", fontSize = 14.sp)
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}