package com.example.pharmafiesta.ui.home.homescreen.drugsearch


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.pharmafiesta.ui.home.BottomNavDestinations
import com.example.pharmafiesta.ui.home.homescreen.druginfo.navigateToDrugInfoScreen
import com.example.pharmafiesta.ui.theme.Green59
import com.example.pharmafiesta.ui.theme.LightGray
import com.example.pharmafiesta.ui.theme.White

private const val TAG = "DrugSearchScreenUi"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrugSearchScreenUi ( navController: NavController,viewModel: DrugSearchViewModel = hiltViewModel()) {
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
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(8.dp)
                .border(BorderStroke(1.dp, LightGray), RoundedCornerShape(30.dp))
                .background(White)
        )
        LazyColumn(/*modifier = Modifier.fillMaxSize()*/) {
            items(drugsList) {drug->
            /*    Log.e(TAG , "name:: ${drug.tradeName}")
            Log.e(TAG , "activeIngredient:: ${drug.activeIngredient}")
            Log.e(TAG , "company:: ${drug.company}")
            Log.e(TAG , "info:: ${drug.info}")
            Log.e(TAG , "price:: ${drug.price}")*/
                Box(modifier = Modifier
                    .clickable {

                        navController.navigateToDrugInfoScreen(tradeName = drug.tradeName?.replace("/","")?:"--", company = drug.company?.replace("/","")?:"--",
                            price = drug.price?.replace("/","")?:"--", group = drug.group?.replace("/","")?:"--", info = drug.info?.replace("/","")?:"--", form = drug.form?.replace("/","")?:"--"
                            , activeIngredient = drug.activeIngredient?.replace("/","")?:"--")

                    }
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp)
                    .border(BorderStroke(1.dp, Green59), RoundedCornerShape(8.dp)),) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(6.dp)
                    ) {
                        Text(
                            text = drug.tradeName.toString(),
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Box(modifier = Modifier.fillMaxWidth()){
                            Text(
                                text = drug.company.toString(),
                                modifier = Modifier
                                    .align(Alignment.CenterStart),
                                fontSize = 12.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "${drug.price.toString()} EGP",
                                modifier = Modifier
                                    .align(Alignment.CenterEnd),
                                color = Green59,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = drug.activeIngredient.toString(),
                            fontSize = 12.sp,
                            color = Color.Gray,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}
