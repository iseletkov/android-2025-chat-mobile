package ru.psu.mobile.mychat.pages

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.psu.mobile.mychat.pages.layout.CViewModelLayout

@Composable
fun PageChat(
    text : String,
    modifier                                : Modifier = Modifier
) {
    //Переименование страницы
    val viewModelLayout                     : CViewModelLayout
            = viewModel(LocalActivity.current as ComponentActivity)
    viewModelLayout.setPageName("Чат")

    Text("Это страница с чатом: $text")
}
