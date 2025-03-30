package com.mainquest.app.ui.screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mainquest.app.core.data.MissionPrefs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainQuestViewModel(application: Application) : AndroidViewModel(application) {

    private val _missionText = MutableStateFlow("")
    val missionText: StateFlow<String> = _missionText

    init {
        viewModelScope.launch {
            MissionPrefs.getMissionStatement(application.applicationContext).collect {
                _missionText.value = it
            }
        }
    }

    fun updateText(newText: String) {
        _missionText.value = newText
    }

    fun clearText() {
        _missionText.value = ""
    }

    fun save() {
        viewModelScope.launch {
            MissionPrefs.saveMissionStatement(getApplication<Application>().applicationContext, _missionText.value)
        }
    }
}