package com.example.pharmafiesta.ui.auth

import com.example.pharmafiesta.utils.Constants.SIGNIN_SCREEN_ROUTE
import com.example.pharmafiesta.utils.Constants.SPLASH_SCREEN_ROUTE
import com.example.pharmafiesta.utils.Constants.SIGNUP_SCREEN_ROUTE

sealed class AuthScreensRoutes(val route:String) {
    object SplashScreenRoute : AuthScreensRoutes(SPLASH_SCREEN_ROUTE)
    object SignupScreenRoute : AuthScreensRoutes(SIGNUP_SCREEN_ROUTE)
    object SignInScreenRoute : AuthScreensRoutes(SIGNIN_SCREEN_ROUTE)
}