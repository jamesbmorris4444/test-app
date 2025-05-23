package com.jbm.testapp.ui
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.jbm.testapp.AppBarState
import com.jbm.testapp.viewmodels.Screen1ViewModel
import com.jbm.testapp.viewstates.Screen1ViewState
import kotlinx.coroutines.launch

@Composable
fun Screen1Screen(
    configAppBar: (AppBarState) -> Unit,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    viewModel: Screen1ViewModel,
    title: String
) {

    val screen1ViewState: Screen1ViewState by viewModel.screen1ViewState.collectAsState()

    fun rocketApiCall(
        viewModel: Screen1ViewModel
    ) {
        val composableScope = viewModel.viewModelScope
        composableScope.launch {
            viewModel.handleIntent(Screen1ViewModel.Screen1Intent.loadScreen1Data)
        }
    }

    @Composable
    fun handleFailure(message: String) {
        viewModel.handleIntent(Screen1ViewModel.Screen1Intent.failureDialogDismissed)

        when {
            screen1ViewState.launchesFailure.isNotEmpty() -> handleFailure(screen1ViewState.launchesFailure)
            //screen1ViewState.launchesAvailable != null -> rocketViewState.launchesAvailable ?.let { RocketLaunchHandler(navigator = navigator, configAppBar = configAppBar, title = title, launches = it) }
            else -> {
                rocketApiCall(viewModel = viewModel)
            }
        }
    }

    @Composable
    fun Screen1Handler(
        canNavigateBack: Boolean,
        navigateUp: () -> Unit,
        configAppBar: (AppBarState) -> Unit,
        title: String,
        data: List<String>
    ) {

        LaunchedEffect(key1 = true) {
            configAppBar(
                AppBarState(
                    title = title,

                    navigationIcon = {
                        if (canNavigateBack) {
//                        IconButton(onClick = navigateUp.also { viewModel.resetDonateProductsScreen() }) {
//                            Icon(
//                                imageVector = Icons.Filled.ArrowBack,
//                                contentDescription = stringResource(R.string.back_button_content_description)
//                            )
//                        }
                        }
                    }
                )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
            }
        }
    }
}