package com.example.proiectsma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CourseApplication()
        }
    }

    @Composable
    fun homeScreen(navigation: NavController) {
        Column(
            modifier = Modifier.padding(0.dp).fillMaxWidth().size(120.dp)
        ) {
            Text(
                text = "Welcome!",
                modifier = Modifier.align(Alignment.CenterHorizontally).offset(y = 160.dp),
                style = TextStyle(fontSize = 25.sp)
            )
        }

        Column(
            modifier = Modifier.padding(0.dp).background(Color.Green).fillMaxWidth().size(120.dp)
        ) {
            Text(
                text = "Course application",
                modifier = Modifier.paddingFromBaseline(top = 80.dp),
                style = TextStyle(fontSize = 25.sp)
            )
        }

        val userNameText = remember { mutableStateOf("") }
        Column(
            modifier = Modifier.offset(x = 85.dp, y = 260.dp).size(220.dp)
        ) {
            TextField(
                value = userNameText.value,
                onValueChange = { userNameText.value = it },
                placeholder = { Text("Numele de utilizator") }
            )
        }

        val UserLoginText: String = "User login"
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                modifier = Modifier.offset(x = 140.dp, y = 320.dp),
                onClick = {navigation.navigate("CourseScreen")}
            ) {
                Text(UserLoginText)
            }
        }

        val AdminUserLoginText: String = "Admin User login"
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                modifier = Modifier.offset(x = 120.dp, y = 460.dp),
                onClick = {}
            ) {
                Text(AdminUserLoginText)
            }
        }

        val userName = remember { mutableStateOf("") }
        Column(
            modifier = Modifier.offset(x = 85.dp, y = 400.dp).size(220.dp)
        ) {
            TextField(
                value = userName.value,
                onValueChange = { userName.value = it },
                placeholder = { Text("Numele de administrator") }
            )
        }

        val SignUp: String = "Sign Up"
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                modifier = Modifier.offset(x = 150.dp, y = 600.dp),
                shape = RoundedCornerShape(16.dp),
                onClick = { navigation.navigate("SignUpScreen") }
            ) {
                Text(SignUp)
            }
        }
    }

    @Composable
    fun toScreens() {
        val navigation: NavHostController = rememberNavController()

        NavHost(
            navController = navigation,
            startDestination = "homeScreen"
        ) {
            composable("homeScreen") {
                homeScreen(navigation)
            }

            composable("SignUpScreen") {
                SignUpScreen()
            }

            composable("CourseScreen"){
                CourseScreen()
            }
        }
    }

    @Composable
    fun CourseApplication(){
      toScreens()
    }
}





