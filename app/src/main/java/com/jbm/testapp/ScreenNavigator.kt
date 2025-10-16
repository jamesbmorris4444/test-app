package com.jbm.testapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jbm.testapp.ui.Screen1Screen
import com.jbm.testapp.ui.ScreenOkScreen
import com.jbm.testapp.viewmodels.Screen1ViewModel

@Composable
fun ScreenNavigator(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "Screen One",
    ) {
        composable(route = "Screen One") {
            Screen1Screen(
                navController = navController,
                viewModel = Screen1ViewModel(),
                screenOkName = "Screen Ok",
                screenErrName = "Screen Err",
                map = ""
            )
        }
        composable(route = "Screen Ok") {
            ScreenOkScreen()
        }
        composable(route = "Screen Err") {
            Screen1Screen(
                navController = navController,
                viewModel = Screen1ViewModel(),
                screenOkName = "Screen Ok",
                screenErrName = "Screen Err",
                map = "err"
            )
        }
    }
}