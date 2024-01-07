package com.example.pharmafiesta.ui.auth

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pharmafiesta.ui.auth.signin.SignInScreenUi
import com.example.pharmafiesta.ui.auth.signup.SignupScreenUi
import com.example.pharmafiesta.ui.home.BottomNavigationActivity
import com.example.pharmafiesta.ui.splash.SplashScreenUi
import com.example.pharmafiesta.ui.theme.PharmaFiestaTheme
import com.example.pharmafiesta.utils.UserPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


private const val TAG = "AuthActivity"

@AndroidEntryPoint
class AuthActivity  : ComponentActivity() {

    @Inject
    lateinit var userPreferences : UserPreferences

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {

        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        askNotificationPermission()
        super.onCreate(savedInstanceState)
        setContent {
            PharmaFiestaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupAppRouteNavigation(userPreferences, loginSuccess = {
                        userPreferences.saveUserLogin("true")
                        startActivity(Intent(this, BottomNavigationActivity::class.java))
                        this.finish()
                    }){ message , isLoggedInSuccess ->
                        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
                        if(isLoggedInSuccess) {
                            userPreferences.saveUserLogin("true")
                            startActivity(Intent(this, BottomNavigationActivity::class.java))
                            this.finish()
                        }
                    }
                }
            }
        }
    }

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {

            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                showNotificationPermissionDialog()
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
    private fun showNotificationPermissionDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Notification Permission")
        alertDialogBuilder.setMessage("Granting notification permission. Would you like to proceed?")
        alertDialogBuilder.setCancelable(true)
        alertDialogBuilder.setPositiveButton("OK") { _, _ ->
            // Request notification permission
            askNotificationPermission()
        }

        alertDialogBuilder.setNegativeButton("No thanks") { _, _ ->
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}

@Composable
private fun SetupAppRouteNavigation(userPreferences: UserPreferences,loginSuccess: () -> Unit,onSignInButtonClicked: (String,Boolean) -> Unit) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AuthScreensRoutes.SplashScreenRoute.route
    ) {
        composable(route = AuthScreensRoutes.SplashScreenRoute.route) {
            Log.d(TAG, "SetupAppRouteNavigation: SplashScreen")
            SplashScreenUi(navController = navController,userPreferences){
                loginSuccess()
            }
        }
        composable(route = AuthScreensRoutes.SplashScreenRoute.route + "/" + AuthScreensRoutes.SignupScreenRoute.route) {
            Log.d(TAG, "SetupAppRouteNavigation: SignupScreen")
            SignupScreenUi(navController = navController)
        }
        composable(route = AuthScreensRoutes.SplashScreenRoute.route + "/${AuthScreensRoutes.SignupScreenRoute.route}" + "/${AuthScreensRoutes.SignInScreenRoute.route}") {
            Log.d(TAG, "SetupAppRouteNavigation: SignInScreenUi")
            SignInScreenUi(onSignInButtonClicked = onSignInButtonClicked)
        }
    }
}