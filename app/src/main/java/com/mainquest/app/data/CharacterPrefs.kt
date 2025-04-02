package com.mainquest.app.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension für DataStore
val Context.characterDataStore by preferencesDataStore(name = "character_prefs")

object CharacterPrefs {
    private val XP_KEY = intPreferencesKey("xp")
    private val NAME_KEY = stringPreferencesKey("name")
    private val AVATAR_KEY = stringPreferencesKey("avatar_id")

    fun getXp(context: Context): Flow<Int> =
        context.characterDataStore.data.map { it[XP_KEY] ?: 0 }

    suspend fun setXp(context: Context, xp: Int) {
        context.characterDataStore.edit { it[XP_KEY] = xp }
    }

    fun getName(context: Context): Flow<String> =
        context.characterDataStore.data.map { it[NAME_KEY] ?: "The Focused One" }

    suspend fun setName(context: Context, name: String) {
        context.characterDataStore.edit { it[NAME_KEY] = name }
    }

    fun getAvatarId(context: Context): Flow<String> =
        context.characterDataStore.data.map { it[AVATAR_KEY] ?: "default_avatar" }

    suspend fun setAvatarId(context: Context, avatarId: String) {
        context.characterDataStore.edit { it[AVATAR_KEY] = avatarId }
    }
}