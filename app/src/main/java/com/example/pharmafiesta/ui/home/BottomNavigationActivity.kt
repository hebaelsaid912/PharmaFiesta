package com.example.pharmafiesta.ui.home

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.pharmafiesta.ui.home.billsscreen.BillsScreenUi
import com.example.pharmafiesta.ui.home.chatscreen.ChatScreenUi
import com.example.pharmafiesta.ui.home.homescreen.HomeScreenUi
import com.example.pharmafiesta.ui.home.homescreen.drugsearch.DrugSearchScreenUi
import com.example.pharmafiesta.ui.home.notificationscreen.NotificationScreenUi
import com.example.pharmafiesta.ui.home.profilescreen.ProfileScreenUi
import com.example.pharmafiesta.ui.theme.Green59
import com.example.pharmafiesta.ui.theme.LightGray
import com.example.pharmafiesta.ui.theme.MintGreen98
import com.example.pharmafiesta.ui.theme.PharmaFiestaTheme
import com.example.pharmafiesta.ui.theme.White

private const val TAG = "BottomNavigationActivity"
class BottomNavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PharmaFiestaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController: NavHostController = rememberNavController()
                    BottomNavigationBar(navController = navController,baseContext)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(navController: NavHostController, baseContext: Context) {
    var selectedItemState = remember { mutableStateOf(true) }

    Scaffold(
        bottomBar = {
            Column {
                Divider(color = LightGray)
                BottomBar(
                    navController = navController,
                    state = selectedItemState,
                    modifier = Modifier
                        .height(56.dp)
                        .padding(horizontal = 4.dp)
                )
            }
        },
        containerColor = White,
        contentColor = White
    ){ paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            NavigationGraph(navController = navController,baseContext)
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, baseContext: Context) {
    NavHost(
        navController,
        startDestination = BottomNavDestinations.BaseHomeScreen.route /*+"/"+BottomNavDestinations.BaseHomeScreen.HomeScreenRoute.route*/
    ) {
        composable(BottomNavDestinations.BaseHomeScreen.route/*+"/"+BottomNavDestinations.BaseHomeScreen.HomeScreenRoute.route*/) {
            HomeScreenUi(navController)
        }
        navigation(
            startDestination = BottomNavDestinations.BaseHomeScreen.HomeScreenRoute.route,
            route = BottomNavDestinations.BaseHomeScreen.route
        ) {
            composable(BottomNavDestinations.BaseHomeScreen.HomeScreenRoute.route) {
                HomeScreenUi(navController)
            }
            composable(
                /*BottomNavDestinations.BaseHomeScreen.route +"/"+BottomNavDestinations.BaseHomeScreen.HomeScreenRoute.route+
                        "/" +*/ BottomNavDestinations.BaseHomeScreen.DrugSearchScreenRoute.route
            ) {
                DrugSearchScreenUi()
            }
            composable(
                /*BottomNavDestinations.BaseHomeScreen.route +"/"+BottomNavDestinations.BaseHomeScreen.HomeScreenRoute.route +
                        "/" +*/ BottomNavDestinations.BaseHomeScreen.MedicinalDosesScreenRoute.route
            ) {

            }
            composable(
                /*BottomNavDestinations.BaseHomeScreen.route +"/"+BottomNavDestinations.BaseHomeScreen.HomeScreenRoute.route +
                        "/" +*/ BottomNavDestinations.BaseHomeScreen.MedicalTestScreenRoute.route
            ) {

            }
            composable(
                /*BottomNavDestinations.BaseHomeScreen.route +"/"+BottomNavDestinations.BaseHomeScreen.HomeScreenRoute.route +
                        "/" +*/ BottomNavDestinations.BaseHomeScreen.DrugInteractionsScreenRoute.route
            ) {

            }
            composable(
                /*BottomNavDestinations.BaseHomeScreen.route +"/"+BottomNavDestinations.BaseHomeScreen.HomeScreenRoute.route +
                        "/" +*/ BottomNavDestinations.BaseHomeScreen.FirstAidScreenRoute.route
            ) {

            }
            composable(
                /*BottomNavDestinations.BaseHomeScreen.route +"/"+BottomNavDestinations.BaseHomeScreen.HomeScreenRoute.route +
                        "/" +*/ BottomNavDestinations.BaseHomeScreen.LaboratoryScreenRoute.route
            ) {

            }
        }

        composable(BottomNavDestinations.ProfileScreen.route) {
            ProfileScreenUi(onSaveProfileDataClicked = {
                Toast.makeText(baseContext, " Saved Successfully ", Toast.LENGTH_LONG).show()
                navController.navigateUp()
            }, onBackClicked = { navController.navigateUp() })
        }
        composable(BottomNavDestinations.NotificationScreen.route) {
            NotificationScreenUi()
        }
        composable(BottomNavDestinations.BillsScreen.route) {
            BillsScreenUi()
        }
        composable(BottomNavDestinations.ChatScreen.route) {
            ChatScreenUi()
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController, state: MutableState<Boolean>, modifier: Modifier = Modifier
) {
    val screens = listOf(
        BottomNavDestinations.BaseHomeScreen,
        BottomNavDestinations.ProfileScreen,
        BottomNavDestinations.NotificationScreen,
        BottomNavDestinations.BillsScreen,
        BottomNavDestinations.ChatScreen
    )

    NavigationBar(
        modifier = modifier,
        containerColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(painter = painterResource(id = screen.icon!!), contentDescription = "")
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Green59,
                    selectedIconColor = White,
                    indicatorColor = MintGreen98,
                )
            )
        }
    }

}