package com.jbm.testapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jbm.testapp.ui.Screen1Screen
import com.jbm.testapp.utils.Constants
import com.jbm.testapp.viewmodels.Screen1ViewModel

data class AppBarState(
    val title: String = "",
    val actions: (@Composable RowScope.() -> Unit)? = null,
    val navigationIcon: (@Composable () -> Unit)? = null
)

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route:String,
)

enum class ScreenNames(val resId: Int) {
    Screen1(R.string.screen1_name),
    Screen2(R.string.screen2_name),
}

// Called one time at app startup
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenNavigator(
    navController: NavHostController,
    quit: () -> Unit
) {
    var appBarState by remember { mutableStateOf(AppBarState()) }
    Scaffold(
        topBar = {
            StartScreenAppBar(appBarState = appBarState)
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { internalPadding ->
        Box(modifier = Modifier.padding(internalPadding)) {
            val screen1Name = stringResource(ScreenNames.Screen1.resId)
            val screen2Name = stringResource(ScreenNames.Screen2.resId)
            NavHost(
                navController = navController,
                startDestination = screen1Name,
            ) {
                composable(route = screen1Name) {
                    Screen1Screen(
                        navController = navController,
                        configAppBar = { appBarState = it },
                        canNavigateBack = navController.previousBackStackEntry != null,
                        navigateUp = { navController.navigateUp() },
                        viewModel = Screen1ViewModel(),
                        title = screen1Name

                    )
                }
                composable(route = screen2Name) {
                    Screen1Screen(
                        navController = navController,
                        configAppBar = { appBarState = it },
                        canNavigateBack = navController.previousBackStackEntry != null,
                        navigateUp = { navController.navigateUp() },
                        viewModel = Screen1ViewModel(),
                        title = screen1Name
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreenAppBar(
    appBarState: AppBarState
) {
    TopAppBar(
        title = { Text(text = appBarState.title, fontSize = 22.sp) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.teal_200)
        ),
        actions = { appBarState.actions?.invoke(this) },
        navigationIcon = { appBarState.navigationIcon?.invoke() }
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = colorResource(R.color.teal_200),
        contentColor = colorResource(R.color.black)

    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Constants.BottomNavItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.id) { inclusive = true}
                    }
                },
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                },
                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = true
            )
        }
    }
}