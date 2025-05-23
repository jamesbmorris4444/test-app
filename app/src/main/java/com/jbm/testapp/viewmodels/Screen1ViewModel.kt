package com.jbm.testapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        data object failureDialogDismissed : Screen1Intent()
    }

    fun handleIntent(intent: Screen1Intent) {
        when (intent) {
            is Screen1Intent.loadScreen1Data -> rocketApiCall()
            is Screen1Intent.failureDialogDismissed -> {
                mutableScreen1iewState.value = Screen1ViewState(
                    launchesFailure = ""
                )
            }
        }
    }

    private fun rocketApiCall() {
        val composableScope = viewModelScope
        composableScope.launch {
            getSpaceXLaunches(composableScope)
        }
    }

    private suspend fun getSpaceXLaunches(composableScope: CoroutineScope) {
        //ApiCall
        mutableScreen1iewState.value = Screen1ViewState(
            launchesAvailable = listOf(),
            launchesFailure = "",
            progressBarState = false
        )
    }
}