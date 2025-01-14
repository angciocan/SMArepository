package com.example.proiectsma

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun StudyingPlatformHomeScreen(navigation: NavController, authentificationViewModel: AuthentificationViewModel){

    val authState = authentificationViewModel.authState.observeAsState()

//    LaunchedEffect(authState.value){
//
//        when(authState.value){
//            is AuthState.Loading -> navigation.navigate("studingPlatformLoginScreen")
//            else -> Unit
//        }
//    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

//        Image(
//            painter = painterResource(id = R.drawable.studyingplatform),
//            contentDescription = null,
//            contentScale = ContentScale.FillBounds,
//            modifier = Modifier.fillMaxSize()
//        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.graduationhat),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.offset(y = (-50).dp).size(300.dp)
            )

            Text(
                text = "Studying Platform",
                style = TextStyle(fontSize = 40.sp),
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.offset(y = (-40).dp)
            )

            val LoginButton = remember { MutableInteractionSource() }
            val LoginButtonClick = LoginButton.collectIsPressedAsState().value

            OutlinedButton(
                onClick = {
                    authentificationViewModel.signOut()
                    navigation.navigate("studyingPlatformLoginScreen")
                },

                modifier = Modifier.fillMaxWidth().offset(y = 140.dp),

                border = BorderStroke(
                    width = 3.dp,
                    color =  if(LoginButtonClick) Color(0xFFFF8B00) else Color.Black
                ),

                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = if(LoginButtonClick) Color(0xFFFF8B00) else Color.Black,
                ),

                interactionSource = LoginButton

                ) {
                Text(text = "Get Started")
            }
        }
    }
}