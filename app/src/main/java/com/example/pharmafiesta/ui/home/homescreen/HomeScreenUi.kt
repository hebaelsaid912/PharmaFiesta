package com.example.pharmafiesta.ui.home.homescreen


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pharmafiesta.R
import com.example.pharmafiesta.ui.theme.Black
import com.example.pharmafiesta.ui.theme.Green59
import com.example.pharmafiesta.ui.theme.LightGray
import com.example.pharmafiesta.ui.theme.White

private const val TAG = "HomeScreenUi"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUi () {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val searchQuery: MutableState<String> = rememberSaveable { mutableStateOf("") }
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

        TextField(
            value = searchQuery.value,
            onValueChange = {
                searchQuery.value = it
            },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = "")
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_text_field_placeholder),
                    color = Color.Gray
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(BorderStroke(1.dp, LightGray), RoundedCornerShape(30.dp))
                .background(White)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            item() {
                HomeItems(homeGridItems, index = 0, iconRes = R.drawable.home_drug_search)
            }
            item() {
                HomeItems(homeGridItems, index = 1, iconRes = R.drawable.home_medicinal_doses)
            }
            item() {
                HomeItems(homeGridItems, index = 2, iconRes = R.drawable.home_medical_tests)
            }
            item() {
                HomeItems(homeGridItems, index = 3, iconRes = R.drawable.home_drug_interactions)
            }
            item() {
                HomeItems(homeGridItems, index = 4, iconRes = R.drawable.home_first_aid)
            }
            item() {
                HomeItems(homeGridItems, index = 5, iconRes = R.drawable.home_laboratory)
            }
        }
    }
}

@Composable
private fun HomeItems(homeGridItems: Array<String>,index:Int,iconRes: Int) {
    Column(
        modifier = Modifier.padding(8.dp),
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