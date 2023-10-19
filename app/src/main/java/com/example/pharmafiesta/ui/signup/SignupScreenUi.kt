package com.example.pharmafiesta.ui.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pharmafiesta.R
import com.example.pharmafiesta.ui.theme.Black
import com.example.pharmafiesta.ui.theme.Green59
import com.example.pharmafiesta.ui.theme.LightGray

private const val TAG = "SignupScreenUi"

@Composable
fun SignupScreenUi (navController: NavController) {
    val scrollState = rememberLazyListState()
    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.signup_img),
                contentDescription = "splash image",
                modifier = Modifier.padding(top = 60.dp)
            )
        }
        item {
            Text(
                text = stringResource(id = R.string.signup_btn),
                color = Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        item {
            SignUpForm()
        }
        item {
            Column(modifier = Modifier.padding(horizontal = 30.dp)) {
                Button(
                    onClick = {
                        // navController.navigate(Screen.SplashScreenRoute.route + "/${Screen.SignupScreenRoute.route}"+ "/${Screen.SignupScreenRoute.route}")
                    },
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
            Row(modifier = Modifier.padding(30.dp)) {
                Text(
                    text = stringResource(id = R.string.do_u_have_account),
                    color = Black,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontSize = 16.sp
                )
                Text(
                    text = stringResource(id = R.string.sign_in_text_btn),
                    color = Green59,
                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
                    fontSize = 16.sp
                )
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

    Column(modifier = Modifier.padding(30.dp), verticalArrangement = Arrangement.spacedBy(26.dp)) {
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
