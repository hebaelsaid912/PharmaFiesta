package com.example.pharmafiesta.ui.home.homescreen.drugsearch


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pharmafiesta.R
import com.example.pharmafiesta.ui.theme.LightGray
import com.example.pharmafiesta.ui.theme.White

private const val TAG = "DrugSearchScreenUi"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrugSearchScreenUi () {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val searchQuery: MutableState<String> = rememberSaveable { mutableStateOf("") }
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

    }
}
