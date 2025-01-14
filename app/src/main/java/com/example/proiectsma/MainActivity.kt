package com.example.proiectsma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
//    override fun onStart() {
//        super.onStart()
//        Intent(this, SessionTimer::class.java).also{
//            intent -> bindService(intent, sessionTimerConnection, Context.BIND_AUTO_CREATE)
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authentificationViewModel : AuthentificationViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
           // val mainScreenViewModel : MainScreenViewModel = hiltViewModel()
           StudyingPlatformApplication(authentificationViewModel)
        }
    }
}

@Composable
fun StudyingPlatformApplication(
    authentificationViewModel: AuthentificationViewModel) {
    StudyingPlatformNavigationToScreens(authentificationViewModel)
}

//private val sessionTimerConnection = object : ServiceConnection{
//
//    private var isBoundTrue = mutableStateOf(false)
//
//    private lateinit var sessionTimerService: SessionTimer
//    override fun onServiceConnected(
//        name: ComponentName?,
//        service: IBinder?
//    ) {
//        val binder = service as SessionTimer.studySessionBinder
//        sessionTimerService = binder.getSessionService()
//        isBoundTrue.value = true
//    }
//
//    override fun onServiceDisconnected(name: ComponentName?) {
//        isBoundTrue.value = false
//    }
//}







