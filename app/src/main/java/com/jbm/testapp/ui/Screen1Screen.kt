package com.jbm.testapp.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jbm.testapp.R
import com.jbm.testapp.viewmodels.Screen1ViewModel
import com.jbm.testapp.viewstates.Screen1ViewState


@Composable
fun Screen1Screen(
    navController: NavHostController,
    viewModel: Screen1ViewModel,
    screenOkName: String,
    screenErrName: String
) {

    val screen1ViewState: Screen1ViewState by viewModel.screen1ViewState.collectAsState()

    val emailText = stringResource(R.string.email_text)
    val passwordText = stringResource(R.string.password_text)
    val titleText = stringResource(R.string.title_text)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = titleText,
            color = colorResource(id = R.color.black),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(start = 32.dp, end = 32.dp),
            value = screen1ViewState.emailText,
            onValueChange = {
                viewModel.handleIntent(Screen1ViewModel.Screen1Intent.changeEmailText(it))
            },
            label = { Text(emailText) }
        )
    }








//    when {
//        screen1ViewState.loginOk -> navController.navigate(screenOkName)
//        else -> navController.navigate(screenErrName)
//
//    }
}