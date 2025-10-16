package com.jbm.testapp.viewstates

import androidx.compose.ui.res.stringResource
import com.jbm.testapp.R
import com.jbm.testapp.repository.storage.Country

data class Screen1ViewState (
    var loginOk: Boolean = true,
    var emailText: String = "",
    var passwordText: String = ""
)