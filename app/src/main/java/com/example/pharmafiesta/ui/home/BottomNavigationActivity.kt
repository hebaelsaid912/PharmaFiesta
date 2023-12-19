package com.example.pharmafiesta.ui.home

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AlertDialog
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
import com.example.pharmafiesta.ui.auth.AuthActivity
import com.example.pharmafiesta.ui.chatbot.ActivityChatBot
import com.example.pharmafiesta.ui.home.calculator.ActivityCalculators
import com.example.pharmafiesta.ui.home.chatscreen.ChatScreenUi
import com.example.pharmafiesta.ui.home.homescreen.HomeScreenUi
import com.example.pharmafiesta.ui.home.homescreen.druginfo.drugInfoRoute
import com.example.pharmafiesta.ui.home.homescreen.drugsearch.DrugSearchScreenUi
import com.example.pharmafiesta.ui.home.homescreen.firstaid.InstructionsScreenUI
import com.example.pharmafiesta.ui.home.homescreen.firstaid.SwallowTheTongueScreenUI
import com.example.pharmafiesta.ui.home.homescreen.newfirstaid.GorzaScreen
import com.example.pharmafiesta.ui.home.homescreen.newfirstaid.IbtlaaScreen
import com.example.pharmafiesta.ui.home.homescreen.newfirstaid.IgmaaScreen
import com.example.pharmafiesta.ui.home.homescreen.newfirstaid.NewFirstAidScreen
import com.example.pharmafiesta.ui.home.laboratores.ActivityLaboratores
import com.example.pharmafiesta.ui.home.medicaltest.ActivityMedicalTest
import com.example.pharmafiesta.ui.home.notificationscreen.NotificationScreenUi
import com.example.pharmafiesta.ui.home.profilescreen.ProfileScreenUi
import com.example.pharmafiesta.ui.theme.Green59
import com.example.pharmafiesta.ui.theme.LightGray
import com.example.pharmafiesta.ui.theme.MintGreen98
import com.example.pharmafiesta.ui.theme.PharmaFiestaTheme
import com.example.pharmafiesta.ui.theme.White
import com.example.pharmafiesta.utils.UserPreferences
import com.example.pharmafiesta.utils.webViewCompose.webViewRoute
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


private const val TAG = "BottomNavigationActivity"

@AndroidEntryPoint
class BottomNavigationActivity : ComponentActivity() {

    @Inject
    lateinit var userPreferences: UserPreferences

    var num = 0
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
                    BottomNavigationBar(userPreferences,navController = navController,baseContext, medicalTestAction = {
                        if(num==0) {
                            val intent = Intent(this, ActivityMedicalTest::class.java)
                            startActivity(intent)
                            finish()
                            num=1
                        }
                    }, medicalDoses = {
                        if(num==0) {
                            val intent = Intent(this, ActivityCalculators::class.java)
                            startActivity(intent)
                            finish()
                            num=1
                        }
                    }, labs = {
                        if(num==0) {
                            val intent = Intent(this, ActivityLaboratores::class.java)
                            startActivity(intent)
                            finish()
                            num=1
                        }
                    }, logout = {

                        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                        builder.setMessage("هل تريد تسجيل الخروج ؟")
                        builder.setTitle("تسجيل خروج !")
                        builder.setCancelable(false)
                        builder.setPositiveButton("نعم",
                            DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
                                userPreferences.saveUserLogin("")
                                val intent = Intent(this, AuthActivity::class.java)
                                startActivity(intent)
                                finish()
                            })
                        builder.setNegativeButton("لا",
                            DialogInterface.OnClickListener { dialog: DialogInterface, which: Int ->
                                dialog.cancel()
                            })
                        val alertDialog: AlertDialog = builder.create()
                       alertDialog.show()

                    }){
                        if(num==0) {
                            val intent = Intent(this, ActivityChatBot::class.java)
                            startActivity(intent)
                            finish()
                            num=1
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(userPreferences: UserPreferences,navController: NavHostController,
                        baseContext: Context,
                        medicalTestAction:()->Unit,
                        medicalDoses:()->Unit,
                        labs:()->Unit,
                        logout:()->Unit,
                        onAction:()->Unit) {
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
            NavigationGraph(userPreferences,navController = navController,baseContext, medicalTestAction = {
                medicalTestAction()
            }, medicalDoses = {medicalDoses()
            }, labs = {
                labs()
            }, logout = {
                logout()
            }){
                onAction()
            }
        }
    }
}

@Composable
fun NavigationGraph(userPreferences: UserPreferences,navController: NavHostController,
                    baseContext: Context,
                    medicalTestAction:()->Unit,
                    medicalDoses:()->Unit,
                    labs:()->Unit,
                    logout:()->Unit,
                    onAction:()->Unit) {
    NavHost(
        navController,
        startDestination = BottomNavDestinations.BaseHomeScreen.route
    ) {
        composable(BottomNavDestinations.BaseHomeScreen.route) {
            HomeScreenUi(navController,userPreferences)
        }
        navigation(
            startDestination = BottomNavDestinations.BaseHomeScreen.HomeScreenRoute.route,
            route = BottomNavDestinations.BaseHomeScreen.route
        ) {
            composable(BottomNavDestinations.BaseHomeScreen.HomeScreenRoute.route) {
                HomeScreenUi(navController,userPreferences)
            }
            composable(
                BottomNavDestinations.BaseHomeScreen.DrugSearchScreenRoute.route
            ) {
                DrugSearchScreenUi(navController)
            }

            drugInfoRoute(navController)

            composable(
                BottomNavDestinations.BaseHomeScreen.MedicalTestScreenRoute.route
            ) {
                medicalTestAction()
            }
            composable(
                BottomNavDestinations.BaseHomeScreen.DrugInteractionsScreenRoute.route
            ) {

            }



            //region new

            composable(
                BottomNavDestinations.BaseHomeScreen.NewFirstAidRoute.route
            ) {
                NewFirstAidScreen(navController,userPreferences)
            }

            composable(
                BottomNavDestinations.BaseHomeScreen.SHARAQANRoute.route
            ) {
                InstructionsScreenUI (navController)
            }

            composable(
                BottomNavDestinations.BaseHomeScreen.BALEELESANRoute.route
            ) {
                SwallowTheTongueScreenUI (navController)
            }

            composable(
                BottomNavDestinations.BaseHomeScreen.IGMAARoute.route
            ) {
                IgmaaScreen(navController)
            }

            composable(
                BottomNavDestinations.BaseHomeScreen.IBTLAARoute.route
            ) {
                IbtlaaScreen(navController)
            }

            composable(
                BottomNavDestinations.BaseHomeScreen.GORAZRoute.route
            ) {
                GorzaScreen(navController)
            }

            webViewRoute(BottomNavDestinations.BaseHomeScreen.WebViewScreen.route,navController)

            //endregion







            composable(
                BottomNavDestinations.BaseHomeScreen.LaboratoryScreenRoute.route
            ) {
                labs()
            }

            composable(
                BottomNavDestinations.BaseHomeScreen.MedicinalDosesScreenRoute.route
            ) {
                medicalDoses()
            }


        }

        composable(BottomNavDestinations.ProfileScreen.route) {
            ProfileScreenUi(userPreferences,onSaveProfileDataClicked = {
                Toast.makeText(baseContext, " Saved Successfully ", Toast.LENGTH_LONG).show()
                navController.navigateUp()
            }, onBackClicked = { navController.navigateUp() }, logout = {
                logout()
            })
        }
        composable(BottomNavDestinations.NotificationScreen.route) {
            NotificationScreenUi()
        }
        composable(BottomNavDestinations.BillsScreen.route) {
            onAction()
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
