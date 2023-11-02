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
import com.pocketpasswords.passwordgeneratorlib.api.DefaultGenerator

class MainActivity : ComponentActivity() {

    val generator = DefaultGenerator().apply {
        setLength(12)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PocketPasswordsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DefaultPreview(generator)
                }
            }
        }
    }
}

@Composable
fun Greeting(generator: DefaultGenerator) {
    val newPassword = remember {
        mutableStateOf(generator.generate().password)
    }
    Column {
        Text(text = "Hello")
        Text(text = newPassword.value)

        Button(
            onClick = {
                newPassword.value = generator.generate().password
            }) {
            Text(text = "GENERATE")
        }
    }
}

@Composable
fun DefaultPreview(generator: DefaultGenerator) {
    PocketPasswordsTheme {
        Greeting(generator)
    }
}