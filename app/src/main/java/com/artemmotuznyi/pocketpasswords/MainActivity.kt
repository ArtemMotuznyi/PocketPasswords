package com.artemmotuznyi.pocketpasswords

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.artemmotuznyi.pocketpasswords.ui.theme.PocketPasswordsTheme
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PocketPasswordsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DefaultPreview()
                }
            }
        }
    }
}

@Composable
fun Greeting(count: Int) {
    val test = remember {
        mutableStateOf(count)
    }
    val test2 = 2
    val res = test2 + test.value

    val tesor = remember {
        mutableStateOf(0)
    }
    Column {
        Text(text = "Hello ${res}, ${tesor.value}!")

        Button(
            onClick = {
                test.value = ++test.value
            }) {
            Text(text = "Update name")
        }
        Button(
            onClick = {
                tesor.value = tesor.value + test.value
            }) {
            Text(text = "Update")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PocketPasswordsTheme {
        Greeting(-2)
    }
}