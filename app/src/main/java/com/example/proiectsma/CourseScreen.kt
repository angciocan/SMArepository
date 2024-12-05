package com.example.proiectsma

//import java.lang.reflect.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CourseScreen(){
    Column(
        modifier = Modifier.padding(0.dp).background(Color.Yellow).fillMaxWidth().size(120.dp)
    ){
        Text(
            text = "Available courses",
            modifier = Modifier.paddingFromBaseline(top = 80.dp),
            style = TextStyle(fontSize = 25.sp)
        )
    }

}




