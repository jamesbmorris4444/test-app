package com.jbm.testapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jbm.testapp.ui.Screen1Screen
import com.jbm.testapp.ui.ScreenOkScreen
import com.jbm.testapp.viewmodels.Screen1ViewModel

enum class ScreenNames(val resId: Int) {
    Screen1(R.string.screen1_name),
    ScreenOk(R.string.screenOk_name),
    ScreenErr(R.string.screenErr_name),
}

// Called one time at app startup
@Composable
fun ScreenNavigator(
    navController: NavHostController
) {
    val screen1Name = stringResource(ScreenNames.Screen1.resId)
    val screenOkName = stringResource(ScreenNames.ScreenOk.resId)
    val screenErrName = stringResource(ScreenNames.ScreenErr.resId)
    NavHost(
        navController = navController,
        startDestination = screen1Name,
    ) {
        composable(route = screen1Name) {
            Log.d("JIMX", "HERE")
            Screen1Screen(
                navController = navController,
                viewModel = Screen1ViewModel(),
                screenOkName = screenOkName,
                screenErrName = screenErrName
            )
        }
        composable(route = screenOkName) {
            ScreenOkScreen()
        }
    }
}