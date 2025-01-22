package ru.psu.mobile.mychat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ru.psu.mobile.mychat.ui.theme.MychatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MychatTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->

                    CalculatorMenu(
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}

@Composable
fun CalculatorMenu(modifier: Modifier = Modifier) {
    var value1 by rememberSaveable  { mutableStateOf("") }
    var value2 by rememberSaveable  { mutableStateOf("") }
    var result by rememberSaveable  { mutableStateOf("") }

    Column {
        TextField(
            value = value1,
            onValueChange = { value1 = it },
            label = { Text("Значение 1") }
        )
        TextField(
            value = value2,
            onValueChange = { value2 = it },
            label = { Text("Значение 2") }
        )

        Row{
            Button(onClick = {
                result = (value1.toDouble()+value2.toDouble()).toString()
            })
            {
                Icon(
                    painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription="123"
                )
                Text("Сложить")
            }
        }
        Text(
            text = result,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview1() {
    MychatTheme {
        CalculatorMenu()
    }
}

//@Preview(showBackground = true, widthDp = 640, heightDp = 480, showSystemUi = true)
//@Composable
//fun GreetingPreview2() {
//    MychatTheme {
//        CalculatorMenu("a d;land an;dn a;kdn ajkw")
//    }
//}