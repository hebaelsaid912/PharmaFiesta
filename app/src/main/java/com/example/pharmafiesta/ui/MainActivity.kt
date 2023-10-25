package com.example.pharmafiesta.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pharmafiesta.ui.auth.signin.SignInScreenUi
import com.example.pharmafiesta.ui.auth.signup.SignupScreenUi
import com.example.pharmafiesta.ui.splash.SplashScreenUi
import com.example.pharmafiesta.ui.theme.PharmaFiestaTheme


private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PharmaFiestaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupAppRouteNavigation()
                }
            }
        }
    }
}

@Composable
private fun SetupAppRouteNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreenRoute.route) {
        composable(route = Screen.SplashScreenRoute.route) {
            Log.d(TAG, "SetupAppRouteNavigation: SplashScreen")
            SplashScreenUi(navController = navController)
        }
        composable(route = Screen.SplashScreenRoute.route + "/" + Screen.SignupScreenRoute.route) {
            Log.d(TAG, "SetupAppRouteNavigation: SignupScreen")
            SignupScreenUi(navController = navController)
        }
        composable(route = Screen.SplashScreenRoute.route + "/${Screen.SignupScreenRoute.route}"+ "/${Screen.SignInScreenRoute.route}") {
            Log.d(TAG, "SetupAppRouteNavigation: SignInScreenUi")
            SignInScreenUi(navController = navController)
        }
    }
}