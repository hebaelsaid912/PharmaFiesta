package com.example.pharmafiesta.ui.home.homescreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pharmafiesta.R
import com.example.pharmafiesta.ui.home.BottomNavDestinations
import com.example.pharmafiesta.ui.theme.Black
import com.example.pharmafiesta.ui.theme.Green59
import com.example.pharmafiesta.ui.theme.LightGray

private const val TAG = "HomeScreenUi"

@Composable
fun HomeScreenUi (navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val homeGridItems = stringArrayResource(id = R.array.home_items_array)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 30.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Welcome",
                fontSize = 16.sp,
                color = Green59,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Column(
                modifier = Modifier.align(Alignment.TopEnd),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
                Text(text = "Toleen", fontSize = 16.sp, color = Green59)
            }
        }

        Spacer(modifier = Modifier.padding(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            item() {
                HomeItems(homeGridItems, index = 0, iconRes = R.drawable.home_drug_search){
                    navController.navigate( BottomNavDestinations.BaseHomeScreen.DrugSearchScreenRoute.route)
                }
            }
            item() {
                HomeItems(homeGridItems, index = 1, iconRes = R.drawable.home_medicinal_doses){
                    navController.navigate( BottomNavDestinations.BaseHomeScreen.MedicinalDosesScreenRoute.route)
                }
            }
            item() {
                HomeItems(homeGridItems, index = 2, iconRes = R.drawable.home_medical_tests){
                    navController.navigate( BottomNavDestinations.BaseHomeScreen.MedicalTestScreenRoute.route)
                }
            }
            item() {
                HomeItems(homeGridItems, index = 3, iconRes = R.drawable.home_drug_interactions){
                    navController.navigate( BottomNavDestinations.BaseHomeScreen.DrugInteractionsScreenRoute.route)
                }
            }
            item() {
                HomeItems(homeGridItems, index = 4, iconRes = R.drawable.home_first_aid){
                    navController.navigate( BottomNavDestinations.BaseHomeScreen.FirstAidScreenRoute.route)
                }
            }
            item() {
                HomeItems(homeGridItems, index = 5, iconRes = R.drawable.home_laboratory){
                    navController.navigate( BottomNavDestinations.BaseHomeScreen.LaboratoryScreenRoute.route)
                }
            }
        }
    }
}

@Composable
private fun HomeItems(homeGridItems: Array<String>,index:Int,iconRes: Int,onItemClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(8.dp)
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
                modifier = Modifier.width(80.dp).height(80.dp)
            )
        }
        Text(text = homeGridItems[index], color = Black)
    }
}