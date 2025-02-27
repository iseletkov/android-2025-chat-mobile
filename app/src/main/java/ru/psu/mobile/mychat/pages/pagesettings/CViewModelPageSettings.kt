package ru.psu.mobile.mychat.pages.pagesettings

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// At the top level of your kotlin file:
val Context.dataStore by preferencesDataStore(name = "settings")

class CViewModelPageSettings(
    private val context                     : Application
)                                           : AndroidViewModel(context)
{
    private val usernameKey                 = stringPreferencesKey("username")
    private val _username                   = MutableStateFlow("")
    var username                            = _username.asStateFlow()

    private val sendReportAutomaticallyKey  = booleanPreferencesKey("saveReportAutomatically")
    private val _sendReportAutomatically    = MutableStateFlow(true)
    val sendReportAutomatically             = _sendReportAutomatically.asStateFlow()

    init{
        viewModelScope.launch {
            val preferences                 = context.dataStore.data
                .first()
            _username.update { preferences[usernameKey] ?: "" }
            _sendReportAutomatically.update{ preferences[sendReportAutomaticallyKey] ?: true }
        }
    }

    fun saveUsername(
        username                            : String
    )
    {
        _username.update { username }
        //        (Dispatchers.IO) Не требуется
        MainScope().launch {
            context.dataStore.edit { preferences ->
                preferences[usernameKey]    = username
            }
        }
    }



    fun saveSendReportAutomatically(
        sendReportAutomatically             : Boolean
    )
    {
        _sendReportAutomatically.update{ sendReportAutomatically }
//        (Dispatchers.IO) Не требуется
        MainScope().launch {
            context.dataStore.edit { preferences ->
                preferences[sendReportAutomaticallyKey] = sendReportAutomatically
            }
        }
    }
}