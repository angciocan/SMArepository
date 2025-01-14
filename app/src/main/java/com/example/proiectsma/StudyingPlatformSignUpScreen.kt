package com.example.proiectsma

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun StudyingPlatformSignUpScreen(navigation: NavController, authentificationViewModel: AuthentificationViewModel){
    val context = LocalContext.current

    val authState = authentificationViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value){

            is AuthState.Loading -> navigation.navigate("studyingPlatformHomeScreen")

            is AuthState.Error ->
                Toast.makeText(context, (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }


        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.graduationhat),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.offset(y = (60).dp).size(200.dp)
                )

                Text(
                    text = "Sign Up",
                    style = TextStyle(
                        fontSize = 35.sp,
                        fontWeight = FontWeight(460),
                        color = Color.Black
                    ),

                    modifier = Modifier.align(Alignment.Start).offset(x = (-15).dp, y = 105.dp)
                )

                val userFullNameText = remember { mutableStateOf("") }
                OutlinedTextField(
                    value = userFullNameText.value,
                    onValueChange = { userFullNameText.value = it },
                    label = {
                        Text(text = "Full name", fontWeight = FontWeight.Bold)
                    },

                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.Person, contentDescription = null)
                    },

                    modifier = Modifier.fillMaxWidth().offset(y = 130.dp),

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Gray,
                        unfocusedLabelColor = Color.Black,
                        cursorColor = Color.Gray
                    )
                )

                val userEmailText = remember { mutableStateOf("") }

                OutlinedTextField(
                    value = userEmailText.value,
                    onValueChange = { userEmailText.value = it },
                    label = {
                        Text(text = "E-mail", fontWeight = FontWeight.Bold)
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.Email, contentDescription = null)
                    },

                    modifier = Modifier.fillMaxWidth().offset(y = 145.dp),

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Gray,
                        unfocusedLabelColor = Color.Black,
                        cursorColor = Color.Gray
                    )
                )

                val visible = remember {mutableStateOf(false)}

                val userPasswordText = remember { mutableStateOf("") }

                OutlinedTextField(
                    value = userPasswordText.value,
                    onValueChange = { userPasswordText.value = it },
                    label = { Text(text = "Password", fontWeight = FontWeight.Bold) },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),

                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.Lock, contentDescription = null)
                    },

                    visualTransformation = if (visible.value) VisualTransformation.None else PasswordVisualTransformation(),

                    trailingIcon = {
                        IconButton(onClick = { visible.value = !visible.value }) {
                            Icon(
                                imageVector = if (visible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (visible.value) "The user password is hidden" else "The user password is printed"
                            )
                        }
                    },

                    modifier = Modifier.fillMaxWidth().offset(y = 160.dp),

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Gray,
                        unfocusedLabelColor = Color.Black,
                        cursorColor = Color.Gray
                    )
                )

                val userConfirmPasswordText = remember { mutableStateOf("") }

                OutlinedTextField(
                    value = userConfirmPasswordText.value,
                    onValueChange = { userConfirmPasswordText.value = it },
                    label = { Text(text = "Confirm password", fontWeight = FontWeight.Bold) },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),

                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.Lock, contentDescription = null)
                    },

                    visualTransformation = if (visible.value) VisualTransformation.None else PasswordVisualTransformation(),

                    trailingIcon = {
                        IconButton(onClick = { visible.value = !visible.value }) {
                            Icon(
                                imageVector = if (visible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (visible.value) "The user password confirmation is hidden" else "The user password confirmation is printed"
                            )
                        }
                    },

                    modifier = Modifier.fillMaxWidth().offset(y = 175.dp),

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Gray,
                        unfocusedLabelColor = Color.Black,
                        cursorColor = Color.Gray
                    )
                )

                val UserSignUpText: String = "Create an account"

                val SignUpButton = remember { MutableInteractionSource() }
                val SignUpButtonClick = SignUpButton.collectIsPressedAsState().value

                OutlinedButton(

                    onClick = {
                        if(userFullNameText.value.isNotBlank() && userPasswordText.value == userConfirmPasswordText.value) {
                            authentificationViewModel.signUp(
                                userEmailText.value,
                                userPasswordText.value
                            )
                        }
                    },

                    modifier = Modifier.fillMaxWidth().offset(y = 200.dp),

                    border = BorderStroke(
                        width = 3.dp,
                        color =  if(SignUpButtonClick) Color(0xFFFF8B00) else Color.Black
                    ),

                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = if(SignUpButtonClick) Color(0xFFFF8B00) else Color.Black,
                    ),

                    interactionSource = SignUpButton
                ) {
                    Text(UserSignUpText)
                }

//                Button(
//                    onClick = {
//                        authentificationViewModel.signUp(
//                            userEmailText.value,
//                            userPasswordText.value
//                        )
//                    }
//                ) {
//                    Text(UserLoginText)
//                }
            }
        }
}

