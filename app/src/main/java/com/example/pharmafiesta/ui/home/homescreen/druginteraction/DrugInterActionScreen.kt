package com.example.pharmafiesta.ui.home.homescreen.druginteraction

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pharmafiesta.R
import com.example.pharmafiesta.ui.home.homescreen.druginfo.navigateToDrugInfoScreen
import com.example.pharmafiesta.ui.home.homescreen.drugsearch.DrugSearchViewModel
import com.example.pharmafiesta.ui.theme.Green59
import com.example.pharmafiesta.ui.theme.LightGray
import com.example.pharmafiesta.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrugInterActionScreen (navController: NavController, viewModel: DrugInterActionViewModel = hiltViewModel()) {
    Column(modifier = Modifier.fillMaxSize()) {
        
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 30.dp, start = 20.dp)
                    .size(24.dp)
                    .clip(CircleShape)
                    .clickable { navController.navigateUp() }
            )

            Text( modifier = Modifier
                .padding(top = 30.dp, start = 10.dp),
                text = "Cure Finder",
                color = Color.Black,
                fontSize = 16.sp)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            val searchQuery: MutableState<String> = rememberSaveable { mutableStateOf("") }
            val drugsList = viewModel.drugList.collectAsState().value
            TextField(
                value = searchQuery.value,
                onValueChange = {
                    searchQuery.value = it
                    viewModel.searchDrugs(searchQuery.value)
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = ""
                    )
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
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(8.dp)
                    .border(BorderStroke(1.dp, LightGray), RoundedCornerShape(30.dp))
                    .background(White)
            )
            LazyColumn(/*modifier = Modifier.fillMaxSize()*/) {
                items(drugsList) { drug ->
                    /*    Log.e(TAG , "name:: ${drug.tradeName}")
                Log.e(TAG , "activeIngredient:: ${drug.activeIngredient}")
                Log.e(TAG , "company:: ${drug.company}")
                Log.e(TAG , "info:: ${drug.info}")
                Log.e(TAG , "price:: ${drug.price}")*/
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                            .border(BorderStroke(1.dp, Green59), RoundedCornerShape(8.dp)),
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(6.dp)
                        ) {
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = drug.tradeName.toString(),
                                color = Color.DarkGray,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    }
                }
            }
        }
    }
}
