package com.example.pharmafiesta.ui.home.profilescreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pharmafiesta.R
import com.example.pharmafiesta.ui.theme.Black
import com.example.pharmafiesta.ui.theme.Green59
import com.example.pharmafiesta.ui.theme.LightGray

private const val TAG = "ProfileScreenUi"

@Composable
fun ProfileScreenUi (onSaveProfileDataClicked:() ->Unit,onBackClicked:() ->Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "",
            modifier = Modifier.size(24.dp).clip(CircleShape).clickable { onBackClicked() }
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.bottomNav_profile_title), fontSize = 24.sp, color = Black)
            Spacer(modifier = Modifier.padding(20.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "",
                    modifier = Modifier.size(115.dp)
                )
                Text(text = "Toleen Ahmed", fontSize = 16.sp, color = Black)
            }
        }
        SignUpForm()

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Button(
                onClick = onSaveProfileDataClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(57.dp)
                    .clip(
                        RoundedCornerShape(30.dp)
                    )
                    .background(Green59),
            ) {
                Text(text = stringResource(id = R.string.submit_btn))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpForm() {

    val userName = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(26.dp)) {
        TextField(
            value = userName.value,
            onValueChange = {
                userName.value = it
            },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.username), contentDescription = "")
            },
            placeholder = {
                Text(text = stringResource(id = R.string.username_text_field_placeholder), color = Color.Gray)
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(LightGray)
        )
        TextField(
            value = email.value,
            onValueChange = {
                email.value = it
            },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.email), contentDescription = "")
            },
            placeholder = {
                Text(text = stringResource(id = R.string.email_text_field_placeholder), color = Color.Gray)
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(LightGray)
        )
        TextField(
            value = phoneNumber.value,
            onValueChange = {
                phoneNumber.value = it
            },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.phone), contentDescription = "")
            },
            placeholder = {
                Text(text = stringResource(id = R.string.phone_text_field_placeholder), color = Color.Gray)
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(LightGray)
        )
        TextField(
            value = password.value,
            onValueChange = {
                password.value = it
            },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.password), contentDescription = "")
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisibility.value = passwordVisibility.value.not() }) {
                    Icon(
                        painter = painterResource(id = if (passwordVisibility.value) R.drawable.password_visibility_on else R.drawable.password_visibility_off),
                        contentDescription = ""
                    )
                }
            },
            placeholder = {
                Text(text = stringResource(id = R.string.password_text_field_placeholder), color = Color.Gray)
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(LightGray)
        )
    }
}
