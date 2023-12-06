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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pharmafiesta.R
import com.example.pharmafiesta.ui.home.BottomNavDestinations

import com.example.pharmafiesta.ui.theme.Black
import com.example.pharmafiesta.ui.theme.LightGray
import com.example.pharmafiesta.utils.UserPreferences

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
                FirstAidItems(title = "حاله الشرقان", iconRes = R.drawable.shraqan) {
                    navController.navigate( BottomNavDestinations.BaseHomeScreen.SHARAQANRoute.route)
                }
            }

            item() {
                FirstAidItems(title = "حاله بلع اللسان", iconRes = R.drawable.image_2) {
                    navController.navigate( BottomNavDestinations.BaseHomeScreen.BALEELESANRoute.route)
                }
            }

            item() {
                FirstAidItems(title = "حاله الاغماء", iconRes = R.drawable.igmaa) {
                    navController.navigate( BottomNavDestinations.BaseHomeScreen.IGMAARoute.route)
                }
            }

            item() {
                FirstAidItems(title = "حاله الابتلاع", iconRes = R.drawable.money) {

                }
            }

            item() {
                FirstAidItems(title = "حالات الخياطه", iconRes = R.drawable.goraz) {

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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
            )
        }
        Text(text = title, color = Black)
    }
}