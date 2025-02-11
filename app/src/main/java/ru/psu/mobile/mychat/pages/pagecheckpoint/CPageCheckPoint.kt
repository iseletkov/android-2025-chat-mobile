package ru.psu.mobile.mychat.pages.pagecheckpoint

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CPageCheckPoint(
    id : String,
    modifier                                : Modifier = Modifier
) {
    Text("Это страница с информацией по контрольной точке: $id")
}