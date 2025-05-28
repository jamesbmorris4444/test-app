package com.jbm.testapp.viewmodels

import androidx.lifecycle.ViewModel
import com.jbm.testapp.repository.Repository
import com.jbm.testapp.viewstates.Screen1ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Screen1ViewModel : ViewModel() {

    private val mutableScreen1ViewState = MutableStateFlow(Screen1ViewState())
    val screen1ViewState: StateFlow<Screen1ViewState> = mutableScreen1ViewState

    sealed class Screen1Intent {
        data object loadScreen1Data : Screen1Intent()
        data object emptyScreenAfterFailure : Screen1Intent()
    }

    fun handleIntent(intent: Screen1Intent) {
        when (intent) {
            is Screen1Intent.loadScreen1Data -> countriesApiCall()
            is Screen1Intent.emptyScreenAfterFailure -> mutableScreen1ViewState.value = Screen1ViewState(countriesAvailable = listOf(), countriesFailure = "")
        }
    }

    private fun countriesApiCall() {
        Repository.getCountryDataList(
            { mutableScreen1ViewState.value = Screen1ViewState(countriesAvailable = it) },
            { mutableScreen1ViewState.value = Screen1ViewState(countriesFailure = it.message ?: "NO FAILURE MESSAGE") }
        )
    }
}