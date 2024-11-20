package com.table.tatu.amparo.services

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_info")

class UserPreferences(context: Context) {
    private val dataStore = context.dataStore

    companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val JWT_TOKEN = stringPreferencesKey("jwt")
    }

    val jwtToken: Flow<String?> = dataStore.data
        .map { preferences -> preferences[JWT_TOKEN] }

    val isLoggedIn: Flow<Boolean> = dataStore.data
        .map { preferences -> preferences[IS_LOGGED_IN] ?: false }

    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = isLoggedIn
        }
    }

    suspend fun setJwtToken(jwt: String) {
        dataStore.edit { preferences ->
            preferences[JWT_TOKEN] = jwt
        }
    }

    suspend fun clearJwtToken() {
        dataStore.edit { preferences ->
            preferences.remove(JWT_TOKEN)
        }
    }
}