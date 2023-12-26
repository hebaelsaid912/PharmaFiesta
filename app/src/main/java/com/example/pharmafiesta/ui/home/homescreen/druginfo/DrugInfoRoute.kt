package com.example.pharmafiesta.ui.home.homescreen.druginfo

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pharmafiesta.ui.home.BottomNavDestinations

fun NavController.navigateToDrugInfoScreen(
    tradeName: String?, company: String, price: String,
    group: String, info: String, form: String,activeIngredient:String
) {
    navigate("${BottomNavDestinations.BaseHomeScreen.DrugInfoScreenRoute.route}/$tradeName/$company/$price/$group/$info/$form/$activeIngredient")
}

fun NavGraphBuilder.drugInfoRoute(navController: NavController) {
    composable(
        "${BottomNavDestinations.BaseHomeScreen.DrugInfoScreenRoute.route}/{${DrugInfoArgs.TRADE_NAME}}/{${DrugInfoArgs.COMPANY}}/{${DrugInfoArgs.PRICE}}/{${DrugInfoArgs.GROUP}}/{${DrugInfoArgs.INFO}}/{${DrugInfoArgs.FORM}}/{${DrugInfoArgs.ACTIVEINGREDIENT}}",
        arguments = listOf(navArgument(DrugInfoArgs.TRADE_NAME) { NavType.StringType },
            navArgument(DrugInfoArgs.COMPANY) { NavType.StringType },
            navArgument(DrugInfoArgs.PRICE) { NavType.StringType },
            navArgument(DrugInfoArgs.GROUP) { NavType.StringType },
            navArgument(DrugInfoArgs.INFO) { NavType.StringType },
            navArgument(DrugInfoArgs.FORM) { NavType.StringType },
            navArgument(DrugInfoArgs.ACTIVEINGREDIENT) { NavType.StringType })
    ) { DrugInfoScreen(navController) }
}

class DrugInfoArgs(savedStateHandle: SavedStateHandle) {

    val tradeName = checkNotNull(savedStateHandle[TRADE_NAME])
    val company = checkNotNull(savedStateHandle[COMPANY])
    val price = checkNotNull(savedStateHandle[PRICE])
    val group = checkNotNull(savedStateHandle[GROUP])
    val info = checkNotNull(savedStateHandle[INFO])
    val form = checkNotNull(savedStateHandle[FORM])
    val activeIngredient = checkNotNull(savedStateHandle[ACTIVEINGREDIENT])

    companion object {
        const val TRADE_NAME = "tradeName"
        const val COMPANY = "company"
        const val PRICE = "price"
        const val GROUP = "group"
        const val INFO = "info"
        const val FORM = "form"
        const val ACTIVEINGREDIENT = "activeIngredient"
    }

}

