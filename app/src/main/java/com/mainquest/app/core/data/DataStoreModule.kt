package com.mainquest.app.core.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "mainquest_prefs"
private val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

object MissionPrefs {
    private val MISSION_KEY = stringPreferencesKey("mission_statement")

    fun getMissionStatement(context: Context): Flow<String> {
        return context.dataStore.data.map { prefs ->
            prefs[MISSION_KEY] ?: ""
        }
    }

    suspend fun saveMissionStatement(context: Context, text: String) {
        context.dataStore.edit { prefs ->
            prefs[MISSION_KEY] = text
        }
    }
}