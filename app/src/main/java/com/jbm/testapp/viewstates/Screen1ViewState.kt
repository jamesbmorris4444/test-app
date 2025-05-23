package com.jbm.testapp.viewstates

data class Screen1ViewState (
    var launchesAvailable: List<String>? = null,
    var launchesFailure: String = "",
    val progressBarState: Boolean = false
)