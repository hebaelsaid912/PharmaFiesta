package com.example.pharmafiesta.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pharmafiesta.R
import com.example.pharmafiesta.ui.auth.AuthScreensRoutes
import com.example.pharmafiesta.ui.theme.Green59
import com.example.pharmafiesta.utils.UserPreferences

private const val TAG = "SplashScreenUi"

@Composable
fun SplashScreenUi(navController: NavController, userPreferences: UserPreferences,loginSuccess:()->Unit) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 60.dp, vertical = 77.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_img),
            contentDescription = "splash image"
        )
        Spacer(modifier = Modifier.padding(77.dp))
        Button(
            onClick = {
                if (userPreferences.getUserLogin().isNotEmpty()) {
                    loginSuccess()
                } else {
                    navController.navigate(AuthScreensRoutes.SplashScreenRoute.route + "/${AuthScreensRoutes.SignupScreenRoute.route}")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(57.dp)
                .clip(
                    RoundedCornerShape(30.dp)
                )
                .background(Green59),
        ) {
            Text(text = stringResource(id = R.string.get_started_btn))
        }

    }

}