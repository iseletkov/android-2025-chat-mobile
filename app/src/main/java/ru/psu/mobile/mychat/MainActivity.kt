package ru.psu.mobile.mychat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.psu.mobile.mychat.ui.theme.MychatTheme
import ru.psu.mobile.mychat.ui.theme.cpurple

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MychatTheme {
                CalculatorPage()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorPage(
    modifier                                : Modifier = Modifier
)
{

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = cpurple,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text("Калькулятор")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {},
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White
                        ),
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            "back"
                        )
                    }
                },
            )
        }
    )
    { innerPadding ->

        //    Column(
//        modifier = modifier
//    ) {
//        Box(modifier = Modifier.background(Color.Red).fillMaxWidth().weight(1f))
//        CalculatorMenu(modifier)
//        Box(modifier = Modifier.background(Color.Red).fillMaxWidth().weight(1f))
//    }
        Box(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        )
        {
            CalculatorMenu(modifier)
        }

    }



}



@Composable
fun CalculatorMenu(
    modifier                                : Modifier = Modifier
) {
    var value1 by rememberSaveable  { mutableStateOf("") }
    var value2 by rememberSaveable  { mutableStateOf("") }
    var result by rememberSaveable  { mutableStateOf("") }

    Column(
        modifier = modifier
            .width(400.dp)
    )
    {
        OutlinedTextField(
            value = value1,
            onValueChange = { value1 = it },
            label = { Text("Значение 1") },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = cpurple,
                focusedTextColor = cpurple,
                unfocusedLabelColor = cpurple,
                focusedLabelColor = cpurple,
                focusedBorderColor= cpurple, // цвет при получении фокуса
                unfocusedBorderColor = cpurple  // цвет при отсутствии фокуса
            ),
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = value2,
            onValueChange = { value2 = it },
            label = { Text("Значение 2") },
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Button(
                onClick = {
                    result = (value1.toDouble()+value2.toDouble()).toString()
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .weight(1f)
                ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = cpurple,
                    contentColor = Color.White
                ),
            )
            {
                Icon(
                    painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription="123"
                )
            }
            Button(
                onClick = {
                    result = (value1.toDouble()+value2.toDouble()).toString()
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(start = 0.dp, end=5.dp)
                    .weight(1f)
                ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = cpurple,
                    contentColor = Color.White
                ),
            )
            {
                Icon(
                    painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription="123"
                )
            }
            Button(
                onClick = {
                    result = (value1.toDouble()+value2.toDouble()).toString()
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(start = 0.dp, end=5.dp)
                    .weight(1f)
                ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = cpurple,
                    contentColor = Color.White
                ),
            )
            {
                Icon(
                    painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription="123"
                )
            }
        }
        Text(
            text = result,
            modifier = Modifier
                .fillMaxWidth()
        )

        val imageModifier = Modifier
            .size(width = 300.dp, height = 200.dp)
            .border(BorderStroke(1.dp, cpurple))
            .background(Color.Yellow)
            .align(alignment = Alignment.CenterHorizontally)
        Image(
            painter = painterResource(id = R.mipmap.dog),
            contentDescription = "Это собака",
            contentScale = ContentScale.Fit,
            modifier = imageModifier,

        )
    }
}

@Preview(showBackground = true, widthDp = 640, heightDp = 480)
@Composable
fun GreetingPreview1() {
    MychatTheme {
        CalculatorPage()
    }
}

//@Preview(showBackground = true, widthDp = 640, heightDp = 480, showSystemUi = true)
//@Composable
//fun GreetingPreview2() {
//    MychatTheme {
//        CalculatorPage()
//    }
//}