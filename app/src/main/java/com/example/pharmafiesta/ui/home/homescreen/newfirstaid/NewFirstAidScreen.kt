package com.example.pharmafiesta.ui.home.homescreen.newfirstaid

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pharmafiesta.R
import com.example.pharmafiesta.ui.home.BottomNavDestinations

import com.example.pharmafiesta.ui.theme.Black
import com.example.pharmafiesta.ui.theme.Green59
import com.example.pharmafiesta.ui.theme.LightGray
import com.example.pharmafiesta.utils.UserPreferences
import com.example.pharmafiesta.utils.webViewCompose.navigateToWebView

@Composable
fun NewFirstAidScreen(navController: NavController,userPreferences:UserPreferences) {

    Column(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .align(Alignment.TopStart)
                        .clickable { navController.navigateUp() }
                )

                Text(modifier = Modifier.align(Alignment.Center),text = "First Aid",
                    color = Green59,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )


                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = userPreferences.getUserInformation().username,
                        fontSize = 14.sp,
                        color = Black
                    )
                }
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            item() {
                FirstAidItems(title = "حاله الشرقان", iconRes = R.drawable.ibtlaanew) {
                    navController.navigate( BottomNavDestinations.BaseHomeScreen.SHARAQANRoute.route)
                }
            }

            item() {
                FirstAidItems(title = "حاله بلع اللسان", iconRes = R.drawable.newswallow) {
                    navController.navigate( BottomNavDestinations.BaseHomeScreen.BALEELESANRoute.route)
                }
            }

            item() {
                FirstAidItems(title = "حاله الاغماء", iconRes = R.drawable.newigmaa) {
                    navController.navigate( BottomNavDestinations.BaseHomeScreen.IGMAARoute.route)
                }
            }

            item() {
                FirstAidItems(title = "حاله الاختناق", iconRes = R.drawable.ibtlaanew) {
                    navController.navigate( BottomNavDestinations.BaseHomeScreen.IBTLAARoute.route)
                }
            }

            item() {
                FirstAidItems(title = "حالات الخياطه", iconRes = R.drawable.gorzanew) {
                    navController.navigate( BottomNavDestinations.BaseHomeScreen.GORAZRoute.route)
                }
            }

            item() {
                FirstAidItems(title = "اسعافات اوليه كامله", iconRes = R.drawable.newigmaa) {
                    navController.navigateToWebView(BottomNavDestinations.BaseHomeScreen.WebViewScreen.route,"https://www.moh.gov.sa/awarenessplateform/FirstAid/Pages/default.aspx")
                }
            }

        }

    }

}

@Composable
private fun FirstAidItems(title: String, iconRes: Int, onItemClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onItemClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
            Image(
                painter = painterResource(id = iconRes), // Replace with your image resource
                contentDescription = null, // Provide a content description if needed
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp) // Set the desired size of the circular image
                    .clip(CircleShape) // Clip the content to a circular shape
                    .background(LightGray),
                contentScale = ContentScale.Crop// Background color (optional)
            )

        Text(text = title, color = Black)
    }
}