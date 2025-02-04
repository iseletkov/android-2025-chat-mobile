package ru.psu.mobile.mychat.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.psu.mobile.mychat.PageInfo
import ru.psu.mobile.mychat.ui.theme.cpurple
import ru.psu.mobile.mychat.util.CBottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CPageLayout()
{
    val navController = rememberNavController()

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
        },

        bottomBar = {
            CBottomNavigationBar(navController = navController)
        }
    )
    { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        //https://www.geeksforgeeks.org/bottom-navigation-bar-in-android-jetpack-compose/
        NavHost(
            navController,
            startDestination = "calculator",
            modifier = modifier
        ) {
            composable("calculator") { CPageCalculator(navController) }
            composable("chat/{text}") { navBackStackEntry ->
                val text = navBackStackEntry.arguments?.getString("text")?:""
                PageChat(text)
            }
            composable("info/{ccc}") { PageInfo() }
        }

    }
}