package com.example.pharmafiesta.ui.home

import com.example.pharmafiesta.R
import com.example.pharmafiesta.utils.Constants.BILLS_SCREEN_ROUTE
import com.example.pharmafiesta.utils.Constants.CHAT_SCREEN_ROUTE
import com.example.pharmafiesta.utils.Constants.HOME_SCREEN_ROUTE
import com.example.pharmafiesta.utils.Constants.NOTIFICATIONS_SCREEN_ROUTE
import com.example.pharmafiesta.utils.Constants.PROFILE_SCREEN_ROUTE


sealed class BottomNavDestinations(
    val route: String,
    val title: Int,
    val icon: Int
) {

    object HomeScreen : BottomNavDestinations(
        route = HOME_SCREEN_ROUTE,
        title = R.string.bottomNav_home_title,
        icon = R.drawable.ic_home
    )

    object ProfileScreen : BottomNavDestinations(
        route = PROFILE_SCREEN_ROUTE,
        title = R.string.bottomNav_profile_title,
        icon = R.drawable.ic_profile
    )

    object NotificationScreen : BottomNavDestinations(
        route = NOTIFICATIONS_SCREEN_ROUTE,
        title = R.string.bottomNav_notification_title,
        icon = R.drawable.ic_notification
    )

    object BillsScreen : BottomNavDestinations(
        route = BILLS_SCREEN_ROUTE,
        title = R.string.bottomNav_bills_title,
        icon = R.drawable.ic_menu
    )

    object ChatScreen : BottomNavDestinations(
        route = CHAT_SCREEN_ROUTE,
        title = R.string.bottomNav_chat_title,
        icon = R.drawable.ic_chat
    )

}
