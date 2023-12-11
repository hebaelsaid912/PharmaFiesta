package com.example.pharmafiesta.utils.webViewCompose

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun NavController.navigateToWebView(destination:String,url:String){
    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
    navigate("$destination/$encodedUrl")
}

fun NavGraphBuilder.webViewRoute(destination:String,navController: NavController){
    composable("$destination/{${WebViewArgs.URL}}",
        arguments = listOf(navArgument(WebViewArgs.URL){ NavType.StringType})
    ){ WebViewScreen(navController) }
}

class WebViewArgs(savedStateHandle: SavedStateHandle){

    val url = checkNotNull(savedStateHandle[URL])

    companion object{
        const val URL = "url"
    }

}
