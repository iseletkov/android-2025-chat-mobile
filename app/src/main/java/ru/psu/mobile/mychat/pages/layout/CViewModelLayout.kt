package ru.psu.mobile.mychat.pages.layout

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class CViewModelLayout                      : ViewModel() {

    private val _pageName                   = MutableStateFlow("")
    val pageName                            = _pageName.asStateFlow()

    fun setPageName(
        name                                : String
    )
    {
        _pageName.update { name }
    }

}