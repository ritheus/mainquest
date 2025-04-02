package com.mainquest.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mainquest.app.data.CharacterPrefs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CharacterViewModel(application: Application) : AndroidViewModel(application) {

    private val _currentXp = MutableStateFlow(0)
    val currentXp: StateFlow<Int> = _currentXp

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _avatarId = MutableStateFlow("default_avatar")
    val avatarId: StateFlow<String> = _avatarId

    init {
        viewModelScope.launch {
            launch {
                CharacterPrefs.getXp(getApplication<Application>().applicationContext).collect { _currentXp.value = it }
            }
            launch {
                CharacterPrefs.getName(getApplication<Application>().applicationContext).collect { _name.value = it }
            }
            launch {
                CharacterPrefs.getAvatarId(getApplication<Application>().applicationContext).collect { _avatarId.value = it }
            }
        }
    }

    fun updateName(newName: String) {
        _name.value = newName
        viewModelScope.launch {
            CharacterPrefs.setName(getApplication<Application>().applicationContext, newName)
        }
    }

    fun updateAvatar(newId: String) {
        _avatarId.value = newId
        viewModelScope.launch {
            CharacterPrefs.setAvatarId(getApplication<Application>().applicationContext, newId)
        }
    }

    val level: StateFlow<Int> = currentXp
        .map { getLevelFromXp(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, getLevelFromXp(currentXp.value))

    val xpToNextLevel: StateFlow<Int> = currentXp
        .map { calculateXpForNextLevel(getLevelFromXp(it)) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, calculateXpForNextLevel(getLevelFromXp(currentXp.value)))

    val xpProgress: StateFlow<Float> = currentXp
        .map { xp ->
            val levelStart = getXpForLevel(getLevelFromXp(xp))
            val levelEnd = calculateXpForNextLevel(getLevelFromXp(xp))
            ((xp - levelStart).toFloat() / (levelEnd - levelStart).toFloat()).coerceIn(0f, 1f)
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0f)

    fun addXp(amount: Int) {
        val newXp = _currentXp.value + amount
        _currentXp.value = newXp

        viewModelScope.launch {
            CharacterPrefs.setXp(getApplication<Application>().applicationContext, newXp)
        }
    }

    // XP-Logik
    private fun getLevelFromXp(xp: Int): Int {
        return when {
            xp < 100 -> 1
            xp < 250 -> 2
            xp < 500 -> 3
            xp < 800 -> 4
            else -> 5 // z. B. max level
        }
    }

    private fun getXpForLevel(level: Int): Int {
        return when (level) {
            1 -> 0
            2 -> 100
            3 -> 250
            4 -> 500
            else -> 800
        }
    }

    private fun calculateXpForNextLevel(level: Int): Int {
        return when (level) {
            1 -> 100
            2 -> 250
            3 -> 500
            4 -> 800
            else -> 1000
        }
    }
}