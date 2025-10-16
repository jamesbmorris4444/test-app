package com.jbm.testapp.viewmodels

import androidx.lifecycle.ViewModel
import com.jbm.testapp.viewstates.Screen1ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Screen1ViewModel() : ViewModel() {

    private val mutableScreen1ViewState = MutableStateFlow(Screen1ViewState())
    val screen1ViewState: StateFlow<Screen1ViewState> = mutableScreen1ViewState

    sealed class Screen1Intent {
        data class changeEmailText(val text: String) : Screen1Intent()
        data class changePasswordText(val text: String) : Screen1Intent()
    }

    fun handleIntent(intent: Screen1Intent) {
        when (intent) {
            is Screen1Intent.changeEmailText -> mutableScreen1ViewState.value = Screen1ViewState(emailText = intent.text)
            is Screen1Intent.changePasswordText -> mutableScreen1ViewState.value = Screen1ViewState(passwordText = intent.text)
        }
    }
}