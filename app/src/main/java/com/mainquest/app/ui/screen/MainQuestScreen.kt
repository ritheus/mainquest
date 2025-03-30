package com.mainquest.app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mainquest.app.R
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun MainQuestScreen(viewModel: MainQuestViewModel = viewModel()) {
    val editableText = viewModel.missionText.collectAsState().value
    val savedText = viewModel.savedMissionText.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var isEditing by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(stringResource(R.string.main_quest_title), fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (savedText.isBlank()) stringResource(R.string.no_mission_statement_placeholder) else savedText,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            if (!isEditing) {
                Button(onClick = { isEditing = true }) {
                    Text(stringResource(R.string.edit))
                }
            }

            if (isEditing) {
                MissionInputField(
                    value = editableText,
                    onValueChange = { viewModel.updateText(it) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    val savedMessage = stringResource(R.string.snackbar_saved)
                    Button(onClick = {
                        viewModel.save()
                        isEditing = false
                        scope.launch {
                            snackbarHostState.showSnackbar(message = savedMessage)
                        }
                    }) {
                        Text(stringResource(R.string.save))
                    }

                    OutlinedButton(onClick = {
                        viewModel.cancelEdit()
                        isEditing = false
                    }) {
                        Text(stringResource(R.string.cancel))
                    }
                }
            }
        }
    }
}

@Composable
fun MissionInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        label = { Text(stringResource(R.string.hint_mission)) },
        singleLine = false,
        maxLines = 10
    )
}
