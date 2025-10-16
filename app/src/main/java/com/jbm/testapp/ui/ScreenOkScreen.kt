package com.jbm.testapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ScreenOkScreen() {

    @Composable
    fun left(
        t1: String,
        t2: String,
        t3: String
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(start = 32.dp, end = 32.dp)) {
            Column {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = t1,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = t2,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = t3,
                    textAlign = TextAlign.Start
                )
            }
            Spacer(modifier = Modifier.width(40.dp))
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red)
            )

        }
    }

    @Composable
    fun right() {
        Row(modifier = Modifier.fillMaxWidth().padding(start = 32.dp, end = 32.dp)) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red)
            )
            Spacer(modifier = Modifier.width(40.dp))
            Column {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Name: Maria Gomez",
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Age: 30",
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Sex: Female",
                    textAlign = TextAlign.End
                )
            }
        }
    }

    @Composable
    fun spaceBar() {
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .height(10.dp)
                .padding(start = 32.dp, end = 32.dp)
                .fillMaxWidth()
                .background(Color.Black)
        )
        Spacer(modifier = Modifier.height(40.dp))
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        left("Name: John Doe", "Age: 30", "Sex: Male")
        spaceBar()
        right()
        spaceBar()
        left("Name: Nitesh Gupta", "Age: 30", "Sex: Male")

    }
}