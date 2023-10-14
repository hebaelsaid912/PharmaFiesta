package com.example.pharmafiesta.ui

import com.example.pharmafiesta.utils.Constants.SPLASH_SCREEN_ROUTE

sealed class Screen(val route:String){
    object SplashScreenRoute:Screen(SPLASH_SCREEN_ROUTE)
}