package ru.psu.mobile.mychat.pages.layout

import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.psu.mobile.mychat.PageInfo
import ru.psu.mobile.mychat.pages.CPageCalculator
import ru.psu.mobile.mychat.pages.PageChat
import ru.psu.mobile.mychat.pages.pagecheckpoint.CPageCheckPoint
import ru.psu.mobile.mychat.pages.pageroute.CPageRoute
import ru.psu.mobile.mychat.pages.pagesettings.CPageSettings
import ru.psu.mobile.mychat.ui.theme.cpurple
import ru.psu.mobile.mychat.util.CBottomNavigationBar
import java.io.File
import java.io.FileWriter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CPageLayout()
{
    val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)

    val file1 = File(
        LocalContext.current.getExternalFilesDir("Custom"), "name.txt"
    )
    Log.d("myfiletest", "file1: ${file1.absolutePath}")
    FileWriter(file1).use { fw ->
        fw.write("Текст для записи в файл");
        fw.flush()
    }
    val file2 = File(
        dir, "name.txt"
    )
    Log.d("myfiletest", "file2: ${file2.absolutePath}")
    FileWriter(file2).use { fw ->
        fw.write("Текст для записи в файл");
        fw.flush()
    }



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
                    //Переименование страницы
                    val viewModelLayout                     : CViewModelLayout
                                                            = viewModel(LocalActivity.current as ComponentActivity)

                    val pageName                            = viewModelLayout.pageName.collectAsState("").value

                    Text(pageName)
//                    Text("123")
                },
                navigationIcon = {
                    //Не работает, т.к. не вызывает перерисовку при навигации.
                    val currentRoute = navController.currentBackStackEntry?.destination?.route?:""
                    if (currentRoute.indexOf("checkpointinfo")==0) {
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
            startDestination = "route",
            modifier = modifier
        ) {
            composable("route") { CPageRoute(navController) }
            composable("checkpointinfo/{id}") { navBackStackEntry ->
                val id = navBackStackEntry.arguments?.getString("id")?:""
                CPageCheckPoint(id)
            }
            composable("calculator") { CPageCalculator(navController) }
            composable("chat/{text}") { navBackStackEntry ->
                val text = navBackStackEntry.arguments?.getString("text")?:""
                PageChat(text)
            }
            composable("info/{ccc}") { PageInfo() }
            composable("settings") { CPageSettings() }
        }

    }
}
