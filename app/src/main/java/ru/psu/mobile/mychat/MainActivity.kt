package ru.psu.mobile.mychat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.psu.mobile.mychat.pages.layout.CPageLayout
import ru.psu.mobile.mychat.ui.theme.MychatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MychatTheme {
                CPageLayout()
            }
        }
    }
}

@Composable
fun PageInfo(
    modifier                                : Modifier = Modifier
) {
    Text("Это страница с информацией")
}

@Preview(showBackground = true, widthDp = 640, heightDp = 480)
@Composable
fun GreetingPreview1() {
    MychatTheme {
        CPageLayout()
    }
}

//@Preview(showBackground = true, widthDp = 640, heightDp = 480, showSystemUi = true)
//@Composable
//fun GreetingPreview2() {
//    MychatTheme {
//        CalculatorPage()
//    }
//}