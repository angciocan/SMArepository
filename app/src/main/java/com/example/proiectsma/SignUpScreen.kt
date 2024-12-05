package com.example.proiectsma

//import java.lang.reflect.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignUpScreen(){

    Column(
        modifier = Modifier.padding(0.dp).fillMaxWidth().size(140.dp)
    ){
        Text(
            text = "Sign Up",
            modifier = Modifier.align(Alignment.CenterHorizontally).offset(y = 100.dp),
            style = TextStyle(fontSize = 25.sp)
        )
    }

    val newName = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.offset(x = 85.dp, y = 200.dp).size(220.dp)
    ){
        TextField(
            value = newName.value,
            onValueChange = { newName.value = it},
            placeholder = { Text("Adaugati un nume") }

        )
    }

    val CreateAccount : String = "Create account"

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Button(
            modifier = Modifier.offset(x = 125.dp, y = 260.dp),
            onClick = {}
        ){
            Text(CreateAccount)
        }
    }
}

