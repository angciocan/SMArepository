package com.example.proiectsma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authentificationViewModel : AuthentificationViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
           StudyingPlatformApplication(authentificationViewModel)
        }
    }
}

@Composable
fun StudyingPlatformApplication(
    authentificationViewModel: AuthentificationViewModel) {
    StudyingPlatformNavigationToScreens(authentificationViewModel)
}








