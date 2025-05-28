package com.jbm.testapp.viewstates

import com.jbm.testapp.repository.storage.Country

data class Screen1ViewState (
    var countriesAvailable: List<Country>? = null,
    var countriesFailure: String = "",
    var emptyScreenAfterfailure: List<Country> = listOf()
)