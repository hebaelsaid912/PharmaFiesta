package com.example.pharmafiesta.ui

import com.example.pharmafiesta.utils.Constants.SIGNIN_SCREEN_ROUTE
import com.example.pharmafiesta.utils.Constants.SPLASH_SCREEN_ROUTE
import com.example.pharmafiesta.utils.Constants.SIGNUP_SCREEN_ROUTE

sealed class Screen(val route:String){
    object SplashScreenRoute:Screen(SPLASH_SCREEN_ROUTE)
    object SignupScreenRoute:Screen(SIGNUP_SCREEN_ROUTE)
    object SignInScreenRoute:Screen(SIGNIN_SCREEN_ROUTE)
}