package com.jbm.testapp.ui
import android.util.Log
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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.jbm.testapp.AppBarState
import com.jbm.testapp.R
import com.jbm.testapp.repository.Repository
import com.jbm.testapp.repository.storage.Fruit
import com.jbm.testapp.viewmodels.Screen1ViewModel
import com.jbm.testapp.viewstates.Screen1ViewState

@Composable
fun Screen1Screen(
    configAppBar: (AppBarState) -> Unit,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    viewModel: Screen1ViewModel,
    title: String
) {

    val screen1ViewState: Screen1ViewState by viewModel.screen1ViewState.collectAsState()

    @Composable
    fun handleFailure(message: String) {
        viewModel.handleIntent(Screen1ViewModel.Screen1Intent.fruitsApiFailure(message))
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
            color = color
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
    fun ListDisplayText(label: String, body: String, color: Color = colorResource(R.color.purple_700)) {
        AnnotatedText(
            modifier = Modifier
                .padding(top = 1.dp),
            text = AnnotatedLabelledStringBuilder(label, body),
            color = color,
            style = MaterialTheme.typography.body1
        )
    }

    @Composable
    fun FruitsElementText(
        name: String,
        id: String,
        family: String,
        order: String,
        genus: String,
        calories: String,
        fat: String,
        sugar: String,
        carbohydrates: String,
        protein: String
    ) {
        ListDisplayText("fruit name: ", name)
        ListDisplayText("fruit id: ", id)
        ListDisplayText("fruit family: ", family)
        ListDisplayText("fruit order: ", order)
        ListDisplayText("fruit genus: ", genus)
        ListDisplayText("calories: ", calories)
        ListDisplayText("fat: ", fat)
        ListDisplayText("sugar: ", sugar)
        ListDisplayText("carbohydrates: ", carbohydrates)
        ListDisplayText("protein: ", protein)
        Divider(modifier = Modifier.padding(top = 4.dp, bottom = 4.dp), color = colorResource(R.color.purple_700), thickness = 2.dp)
    }

    @Composable
    fun FruitsHandler(
        configAppBar: (AppBarState) -> Unit,
        title: String,
        fruits: List<Fruit>
    ) {
        @Composable
        fun LaunchesList(fruits: List<Fruit>) {
            Spacer(modifier = Modifier.height(4.dp))
            LazyColumn {
                fruits.forEachIndexed { index, _ ->
                    item {
                        with (fruits[index]) {
                            FruitsElementText(
                                name,
                                id.toString(),
                                family,
                                order,
                                genus,
                                nutritions.calories.toString(),
                                nutritions.fat.toString(),
                                nutritions.sugar.toString(),
                                nutritions.carbohydrates.toString(),
                                nutritions.protein.toString(),
                            )
                        }
                    }
                }
            }
        }

        LaunchedEffect(key1 = true) {
            configAppBar(
                AppBarState(
                    title = title,
//                    navigationIcon = {
//                        if (canNavigateBack) {
//                            IconButton(onClick = navigateUp.also { viewModel.resetDonateProductsScreen() }) {
//                                Icon(
//                                    imageVector = Icons.Filled.ArrowBack,
//                                    contentDescription = ""
//                                )
//                            }
//                        }
//                    }
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
                LaunchesList(fruits)
            }
        }
    }

    Log.d("JIMX", "fail=${screen1ViewState.fruitsFailure }")
    Log.d("JIMX", "avail=${screen1ViewState.fruitsAvailable }")

    when {
        screen1ViewState.fruitsFailure.isNotEmpty() -> handleFailure(screen1ViewState.fruitsFailure)
        screen1ViewState.fruitsAvailable != null -> screen1ViewState.fruitsAvailable ?.let {
            FruitsHandler(
                configAppBar = configAppBar,
                title = title,
                fruits = it)
        } else -> {
            CircularProgressBar()
            viewModel.fruitsApiCall()
        }
    }
}