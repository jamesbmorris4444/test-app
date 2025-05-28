package com.jbm.testapp.viewstates

import com.jbm.testapp.repository.storage.Fruit

data class Screen1ViewState (
    var fruitsAvailable: List<Fruit>? = null,
    var fruitsFailure: String = "",
    var emptyScreenAfterfailure: List<Fruit> = listOf()
)