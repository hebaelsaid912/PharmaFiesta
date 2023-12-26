package com.example.pharmafiesta.ui.home.homescreen.book

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pharmafiesta.R

@Composable
fun BookScreen(navController:NavController) {
    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 30.dp, start = 20.dp)
                    .size(24.dp)
                    .clip(CircleShape)
                    .clickable { navController.navigateUp() }
            )

            Text(
                modifier = Modifier
                    .padding(top = 30.dp, start = 10.dp), text = "Notes",
                color = Color.Black,
                fontSize = 16.sp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .verticalScroll(rememberScrollState())
        ) {


        }

    }

}
