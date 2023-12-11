package com.example.pharmafiesta.utils.webViewCompose

import android.annotation.SuppressLint
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pharmafiesta.R
import com.example.pharmafiesta.utils.LoadingDialog
import com.example.pharmafiesta.utils.webViewCompose.viewmodel.WebViewViewModel
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import kotlinx.coroutines.delay

@Composable
fun WebViewScreen(
    navController: NavController,
    webViewViewModel: WebViewViewModel = hiltViewModel()
) {
    val webViewState by webViewViewModel.state.collectAsState()
    WebViewContent(navController, webViewState)
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewContent(navController: NavController, webViewState: WebViewObject) {
    var isLoading by remember { mutableStateOf(true) }
    if (isLoading) {
        LoadingDialog()
    }

    LaunchedEffect(key1 = isLoading) {
        delay(2000)
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        val webViewSate = rememberWebViewState(url = webViewState.url)
        val webClient = remember { MyClient() }

        Box(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .align(Alignment.TopStart)
                    .clickable { navController.navigateUp() }
            )

        }

        Spacer(modifier = Modifier.height(3.dp))

        WebView(
            state = webViewSate,
            modifier = Modifier.fillMaxWidth(),
            onCreated = {
                it.settings.javaScriptEnabled = true
            },
            client = webClient
        )

    }
}

class MyClient : AccompanistWebViewClient() {
    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        return super.shouldInterceptRequest(view, request)
    }
}