package com.example.pharmafiesta.ui.home.homescreen.newfirstaid

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pharmafiesta.R
import com.example.pharmafiesta.ui.home.BottomNavDestinations
import com.example.pharmafiesta.ui.theme.Black
import com.example.pharmafiesta.ui.theme.Green59
import com.example.pharmafiesta.utils.GifScreen
import com.example.pharmafiesta.utils.webViewCompose.navigateToWebView

@Composable
fun IbtlaaScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .align(Alignment.TopStart)
                    .clickable { navController.navigateUp() }
            )

        }

        LazyColumn(
            modifier = Modifier
            //  .fillMaxSize()
            //   .padding(16.dp)
        ) {
            item {
                Column(/*modifier = Modifier.fillMaxWidth(),*/
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    GifScreen("https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExbGVocnh4eDNjdndrYWQ1eXptMWh4eHU0Mjd3MGRmZHd6ZjdqMm93eCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/6tJGqgppMKzzvixdIF/giphy.gif")

                    Spacer(modifier = Modifier.height(5.dp))

                    GifScreen("https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExdjNrN3gwMnpkajhvaGhkd3Q3MXdoeHd0ZWJqNGppM25pemVscW95dyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/tBWEXfUjO31CJvC2lN/giphy.gif")

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "الاختناق (بلع جسم غريب)",
                        fontSize = 24.sp,
                        color = Black
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "إذا ابتلع طفل جسم غريب\n" +
                                    " اضربه أربع ضربات على أعلى الظهر.\n" +
                                    " عدل وضع الطفل بحيث يكون مستلقيا على ظهره على فخذ المسعف وأسند رأسه بإحدى يديك.\n" +
                                    " اضغط بإصبعين بين السرة وأسفل القفص الصدري إلى الأعلى أربع ضغطات قوية.\n" +
                                    " افتح فم الطفل لرؤية أي جسم غريب وأزل كل ما تراه في الفم."
                            ,fontSize = 16.sp,
                            color = Black,
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    GifScreen("https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExeHY2MXRuOTBieGM3dzY5anoxa3ZhdGpyZzU0MG91cjYxMnU3bGp2dyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/88e13QeuhDIeFgJq5p/giphy-downsized-large.gif")

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = {
                            navController.navigateToWebView(
                                BottomNavDestinations.BaseHomeScreen.WebViewScreen.route,
                                "https://www.moh.gov.sa/awarenessplateform/FirstAid/Pages/Choking.aspx")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Green59)
                    ) {
                        androidx.compose.material.Text(
                            text = "Show Source",
                            fontSize = 12.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))


                }
            }

        }

    }

}