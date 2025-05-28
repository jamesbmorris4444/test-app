package com.jbm.testapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jbm.testapp.AppBarState
import com.jbm.testapp.R
import com.jbm.testapp.repository.storage.Country
import com.jbm.testapp.viewmodels.Screen1ViewModel
import com.jbm.testapp.viewstates.Screen1ViewState

@Composable
fun Screen1Screen(
    configAppBar: (AppBarState) -> Unit,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    navController: NavHostController,
    viewModel: Screen1ViewModel,
    title: String
) {

    val screen1ViewState: Screen1ViewState by viewModel.screen1ViewState.collectAsState()

    @Composable
    fun handleFailure(message: String) {
        AlertDialog(
            onDismissRequest = { viewModel.handleIntent(Screen1ViewModel.Screen1Intent.emptyScreenAfterFailure) },
            title = { Text(text = "Alert Dialog") },
            text = { Text(text = message) },
            confirmButton = {
                Button(
                    onClick = { viewModel.handleIntent(Screen1ViewModel.Screen1Intent.emptyScreenAfterFailure) }
                ) {
                    Text(
                        text = "Confirm",
                        color = Color.White
                    )
                }
            }
        )
    }

    @Composable
    fun CircularProgressBar() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(60.dp),
                color = MaterialTheme.colors.primary,
                strokeWidth = 6.dp
            )
        }
    }

    @Composable
    fun AnnotatedText(
        modifier: Modifier = Modifier,
        textAlign: TextAlign? = null,
        text: AnnotatedString= AnnotatedString(""),
        style: TextStyle = MaterialTheme.typography.body2,
        color: Color = Color.Unspecified
    ) {
        Text(
            modifier = modifier,
            textAlign = textAlign,
            text = text,
            style = style,
            color = color,
            fontSize = 20.sp
        )
    }

    @Composable
    fun AnnotatedLabelledStringBuilder(label: String, body: String): AnnotatedString {
        return buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("$label: ") }
            append(body)
        }
    }

    @Composable
    fun ListDisplayText(label: String, body: String, color: Color = colorResource(R.color.black)) {
        AnnotatedText(
            text = AnnotatedLabelledStringBuilder(label, body),
            color = color,
            style = MaterialTheme.typography.body1
        )
    }

    @Composable
    fun CountriesElementText(
        name: String,
        region: String,
        code: String,
        capital: String
    ) {
        Text("")
        Text("$name, $region          $code")
        Text("")
        Text(capital)
        Text("")
        Divider(modifier = Modifier.padding(top = 4.dp, bottom = 4.dp), color = colorResource(R.color.purple_200), thickness = 2.dp)
    }

    @Composable
    fun CountriesHandler(
        configAppBar: (AppBarState) -> Unit,
        title: String,
        countries: List<Country>
    ) {
        @Composable
        fun LaunchesList(countries: List<Country>) {
            Spacer(modifier = Modifier.height(4.dp))
            Divider(modifier = Modifier.padding(top = 4.dp, bottom = 4.dp), color = colorResource(R.color.purple_200), thickness = 2.dp)
            LazyColumn {
                countries.forEachIndexed { index, _ ->
                    item {
                        with (countries[index]) {
                            CountriesElementText(
                                name,
                                region,
                                code,
                                capital
                            )
                        }
                    }
                }
            }
        }

        //navController.popBackStack(route = donateProductsSearchStringName, inclusive = true)
        //                                        navController.navigate(createProductsStringName)

        LaunchedEffect(key1 = true) {
            configAppBar(
                AppBarState(
                    title = title,
                    navigationIcon = {
                        if (canNavigateBack) {
                            IconButton(onClick = navigateUp) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = ""
                                )
                            }
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
                LaunchesList(countries)
            }
        }
    }

    when {
        screen1ViewState.countriesFailure.isNotEmpty() -> handleFailure(screen1ViewState.countriesFailure)
        screen1ViewState.countriesAvailable != null -> screen1ViewState.countriesAvailable ?.let {
            CountriesHandler(
                configAppBar = configAppBar,
                title = title,
                countries = it)
        } else -> {
            CircularProgressBar()
            viewModel.handleIntent(Screen1ViewModel.Screen1Intent.loadScreen1Data)
        }
    }
}