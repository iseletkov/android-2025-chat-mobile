package ru.psu.mobile.mychat.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PageChat(
    text : String,
    modifier                                : Modifier = Modifier
) {
    Text("Это страница с чатом: $text")
}
