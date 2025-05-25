package com.jbm.testapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbm.testapp.repository.Repository
import com.jbm.testapp.viewstates.Screen1ViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class Screen1ViewModel : ViewModel() {

    private val mutableScreen1iewState = MutableStateFlow(Screen1ViewState())
    val screen1ViewState: StateFlow<Screen1ViewState> = mutableScreen1iewState

    sealed class Screen1Intent {
        data object loadScreen1Data : Screen1Intent()
        data class fruitsApiFailure(val message: String) : Screen1Intent()
    }

    fun handleIntent(intent: Screen1Intent) {
        when (intent) {
            is Screen1Intent.loadScreen1Data -> fruitsApiCall()
            is Screen1Intent.fruitsApiFailure -> {
                mutableScreen1iewState.value = Screen1ViewState(
                    fruitsFailure = intent.message
                )
            }
        }
    }

    fun fruitsApiCall() {
        Repository.getFruitDataList(
            { mutableScreen1iewState.value = Screen1ViewState(fruitsAvailable = it) },
            { mutableScreen1iewState.value = Screen1ViewState(fruitsFailure = it.message ?: "NO FAILURE MESSAGE") }
        )
    }
}