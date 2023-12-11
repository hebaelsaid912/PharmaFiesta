package com.example.pharmafiesta.utils.webViewCompose.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.pharmafiesta.utils.webViewCompose.WebViewArgs
import com.example.pharmafiesta.utils.webViewCompose.WebViewObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(WebViewObject())
    val state = _state.asStateFlow()

    private val args = WebViewArgs(savedStateHandle)

    init {
        getWebViewArgs()
    }

    private fun getWebViewArgs() {
        _state.update { it.copy(url = "${args.url}") }
    }

}
