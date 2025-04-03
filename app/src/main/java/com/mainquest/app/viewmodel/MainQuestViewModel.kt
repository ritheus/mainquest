package com.mainquest.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mainquest.app.core.data.MissionPrefs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainQuestViewModel(application: Application) : AndroidViewModel(application) {

    private val _savedMissionText = MutableStateFlow("") // dauerhaft gespeicherter Text
    val savedMissionText: StateFlow<String> = _savedMissionText

    private val _editableText = MutableStateFlow("")     // gerade im Textfeld
    val missionText: StateFlow<String> = _editableText

    init {
        viewModelScope.launch {
            MissionPrefs.getMissionStatement(application.applicationContext).collect {
                _savedMissionText.value = it
                _editableText.value = it
            }
        }
    }

    fun updateText(newText: String) {
        _editableText.value = newText
    }

    fun cancelEdit() {
        _editableText.value = _savedMissionText.value
    }

    fun save() {
        viewModelScope.launch {
            val text = _editableText.value
            MissionPrefs.saveMissionStatement(
                getApplication<Application>().applicationContext,
                text
            )
            _savedMissionText.value = text
        }
    }
}