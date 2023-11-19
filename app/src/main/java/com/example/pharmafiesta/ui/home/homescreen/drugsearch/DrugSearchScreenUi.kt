package com.example.pharmafiesta.ui.home.homescreen.drugsearch


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pharmafiesta.R
import com.example.pharmafiesta.ui.theme.Green59
import com.example.pharmafiesta.ui.theme.LightGray
import com.example.pharmafiesta.ui.theme.White

private const val TAG = "DrugSearchScreenUi"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrugSearchScreenUi ( viewModel: DrugSearchViewModel = hiltViewModel()) {
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
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(BorderStroke(1.dp, LightGray), RoundedCornerShape(30.dp))
                .background(White)
        )
        LazyColumn {
            items(drugsList) {drug->
                Log.e(TAG , "name:: ${drug.tradeName}")
            Log.e(TAG , "activeIngredient:: ${drug.activeIngredient}")
            Log.e(TAG , "company:: ${drug.company}")
            Log.e(TAG , "info:: ${drug.info}")
            Log.e(TAG , "price:: ${drug.price}")
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            White
                        )
                ) {
                    Text(
                        text = drug.tradeName.toString(),
                        modifier = Modifier.size(14.dp),
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )
                    Box(modifier = Modifier.fillMaxWidth()){
                        Text(
                            text = drug.company.toString(),
                            modifier = Modifier.size(12.dp).align(Alignment.CenterStart),
                            color = Color.Gray
                        )
                        Text(
                            text = drug.price.toString(),
                            modifier = Modifier.size(12.dp).align(Alignment.CenterEnd),
                            color = Green59,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = drug.activeIngredient.toString(),
                        modifier = Modifier.size(12.dp),
                        color = Color.Gray,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
