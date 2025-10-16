package com.jbm.testapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jbm.testapp.viewmodels.Screen1ViewModel
import com.jbm.testapp.viewstates.Screen1ViewState


@Composable
fun Screen1Screen(
    navController: NavHostController,
    viewModel: Screen1ViewModel,
    screenOkName: String,
    screenErrName: String,
    map: String
) {

    val screen1ViewState: Screen1ViewState by viewModel.screen1ViewState.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Employee Directory",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(start = 32.dp, end = 32.dp),
            value = screen1ViewState.emailText,
            onValueChange = {
                viewModel.handleIntent(Screen1ViewModel.Screen1Intent.changeEmailText(it))
            },
            label = { Text("Admin Email") }
        )
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(start = 32.dp, end = 32.dp),
            value = screen1ViewState.passwordText,
            onValueChange = {
                viewModel.handleIntent(Screen1ViewModel.Screen1Intent.changePasswordText(it))
            },
            label = { Text("Admin Password") }
        )
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedButton(onClick = {
            if (screen1ViewState.passwordText == "pass") {
                navController.navigate(screenOkName)
            } else {
                navController.navigate(screenErrName)
            }
        }) {
            Text("Login")
        }
        if (map.isNotEmpty()) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Sorry, either email or password are wrong. Try again.",
                textAlign = TextAlign.Center
            )
        }
    }
}