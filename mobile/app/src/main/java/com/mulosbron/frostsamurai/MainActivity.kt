package com.mulosbron.frostsamurai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mulosbron.frostsamurai.ui.theme.ComposeIntroTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeIntroTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen("Frost Samurai Init")
                }
            }
        }
    }

    @Composable
    fun MainScreen(text: String) {

        // Column, Row ve Box
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomText(text = text)
            Spacer(modifier = Modifier.padding(5.dp))
            CustomText(text = "Hello World!")
            Spacer(modifier = Modifier.padding(5.dp))
            CustomText(text = "Hello Kotlin!")
            Spacer(modifier = Modifier.padding(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomText(text = "Row1")
                Spacer(modifier = Modifier.padding(5.dp))
                CustomText(text = "Row2")
                Spacer(modifier = Modifier.padding(5.dp))
                CustomText(text = "Row3")
            }
        }

    }

    @Composable
    fun CustomText(text: String) {
        Text(
            text = text,
            modifier = Modifier
                .clickable { println("clicked") }
                .background(color = Color.Cyan)
                .padding(horizontal = 5.dp, vertical = 5.dp),
            //.width(150.dp),
            //size, sizeIn
            //.height(250.dp)
            //.fillMaxSize(fraction = 0.25f),
            color = Color.Blue,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen(text = "Frost Samurai Init")
    }
}
