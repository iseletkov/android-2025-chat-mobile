package ru.psu.mobile.mychat.util

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.psu.mobile.mychat.R


@Composable
fun CBottomNavigationBar(navController: NavHostController) {
//    (
//
//            // set background color
//            backgroundColor = Color(0xFF0F9D58)
//            )
    NavigationBar  {

        // observe the backstack
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // observe current route to change the icon
        // color,label color when navigated
        val currentRoute = navBackStackEntry?.destination?.route

        // Bottom nav items we declared
        NavBarItems.BarItems.forEach { navItem ->

            // Place the bottom nav items
            NavigationBarItem(

                // it currentRoute is equal then its selected route
                selected = currentRoute == navItem.route,

                // navigate on click
                onClick = {
                    navController.navigate(navItem.route)
                    //Мешает навигации с параметром.
//                    {
//                        popUpTo(navController.graph.findStartDestination().id) {saveState = true}
//                        launchSingleTop = true
//                        restoreState = true
//                    }
                },

                // Icon of navItem
                icon = {
                    Icon(
                        painter = painterResource(navItem.image),
                        contentDescription = navItem.title
                    )
                },

                // label
                label = {
                    Text(text = navItem.title)
                },
                alwaysShowLabel = false
            )
        }
    }
}
//Список всех кнопок навигации
object NavBarItems {
    val BarItems = listOf(
        CBarItem(
            title = "Route",
            image = R.drawable.baseline_route_24,
            route = "route"
        ),
        CBarItem(
            title = "Calculator",
            image = R.drawable.baseline_calculate_24,
            route = "calculator"
        ),
        CBarItem(
            title = "Chat",
            image = R.drawable.baseline_chat_24,
            route = "chat/123"
        ),
        CBarItem(
            title = "Info",
            image = R.drawable.baseline_info_24,
            route = "info/gggg"
        )
    )
}
//Описание одной кнопочки из навигации
data class CBarItem(
    val title: String,
    val image: Int,
    val route: String
)