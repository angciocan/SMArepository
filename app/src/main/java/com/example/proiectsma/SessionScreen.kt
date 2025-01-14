package com.example.proiectsma

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionScreen(
    navigation: NavController
){
    val context = LocalContext.current
    val subjectListScope = rememberCoroutineScope()
    val subjectSheetState = rememberModalBottomSheetState()
    var isButtonSubjectSheet = remember{
        mutableStateOf(false)
    }
    var isDeleteDialogOpen = rememberSaveable { mutableStateOf(false) }

    var sessionTimeInSeconds = remember{ mutableStateOf(0) }
    var sessionTimeRunning = remember{ mutableStateOf(false) }

    val subject = listOf(
        Subject(subjectId = 0, subjectName = "Math", studiedHours = 11f),
        Subject(subjectId = 0, subjectName = "Physics", studiedHours = 15f),
        Subject(subjectId = 0, subjectName = "History", studiedHours = 8f),
        Subject(subjectId = 0, subjectName = "Science", studiedHours = 15f),
        Subject(subjectId = 0, subjectName = "Programming", studiedHours = 9f),
        Subject(subjectId = 0, subjectName = "Electronics", studiedHours = 9f)
    )

    SubjectList(
        subjectListState = subjectSheetState,
        isOpen = isButtonSubjectSheet.value,
        subjects = subject,
        onButtonClicked = {
            subjectListScope.launch {
                subjectSheetState.hide()
            }.invokeOnCompletion {
                if(!subjectSheetState.isVisible) isButtonSubjectSheet.value = false
            }
        },
        onDismissSubject = {
            isButtonSubjectSheet.value = false
        }
    )

    DeleteSessionDialog(
        isActive = isDeleteDialogOpen.value,
        DialogTitle = "Delete session",
        Text = "Are you sure to delete this session?",
        onDismissButtonClick = { isDeleteDialogOpen.value = false },
        onConfirmButtonClick = { isDeleteDialogOpen.value = false }
    )

    val sessions = listOf(
        Session(
            SessionId = 0,
            SessionSubjectId = 0,
            SessionDate = 0L,
            SessionDuration = 2,
            SessionSubject = "Math"
        )
    )

    LaunchedEffect(sessionTimeRunning.value){
            while (sessionTimeRunning.value) {
                delay(1000L)
                sessionTimeInSeconds.value++
            }
    }

    Scaffold (
        topBar = {
             SessionUpScreen (
                 onExitButtonClick = {},
                 navigation = navigation
             )
        }
    ){paddingValues ->
        LazyColumn(
             modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {

            item {
                Timekeeper(modifier = Modifier.fillMaxWidth().aspectRatio(1f), sessionTimeInSeconds = sessionTimeInSeconds.value)
            }

            item{
                ActualSubjects(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                    forSubject = "Math",
                    chooseSubjectClick = {
                        isButtonSubjectSheet.value = true
                    }
                )
            }

            item{
                TimeButtons(
                    sessionTimeRunning = sessionTimeRunning.value,
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    startButtonClick = {
                        sessionTimeRunning.value = true
                    },

                    finishButtonClick = {
                        sessionTimeRunning.value = false
                    },

                    cancelButtonClick = {
                        sessionTimeRunning.value = false
                        sessionTimeInSeconds.value = 0
                    }
                )
            }

            SessionList(
                SessionTitle = "Last study sessions",
                SessionEmptyListText = "There are not sessions at this moment!",
                SessionList = sessions,
                onDeleteSessionClick = { isDeleteDialogOpen.value = true }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionUpScreen(
    onExitButtonClick: () -> Unit,
    navigation: NavController
){
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {navigation.navigate("studyingPlatformSubjectsScreen")}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Exit from session screen"
                )
            }
        },

        title = {
            Text(text = "Session")
        }
    )
}

@Composable
fun Timekeeper(
    modifier: Modifier,
    sessionTimeInSeconds: Int,
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){

        Box(
            modifier = Modifier.size(250.dp).border(5.dp, Color(0xFFFF8B00), CircleShape)
        )

        Text(
            text = timeString(sessionTimeInSeconds),
            fontSize = 45.sp
        )
    }

}

@Composable
fun ActualSubjects(
    modifier: Modifier,
    forSubject: String,
    chooseSubjectClick: () -> Unit
){
    Column(
         modifier = modifier
    ) {

        Text(
            text = "For subject",
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = forSubject,
                //textSize = Modifier.size(14.dp)
            )

            IconButton(
                onClick = chooseSubjectClick
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Choose a subject"
                )
            }
        }
    }
}

@Composable
fun TimeButtons(
    modifier: Modifier,
    sessionTimeRunning: Boolean,
    startButtonClick: () -> Unit,
    cancelButtonClick: () -> Unit,
    finishButtonClick: () -> Unit
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        val cancelSessionButton = remember { MutableInteractionSource() }
        val cancelSessionButtonClick = cancelSessionButton.collectIsPressedAsState().value

        val startSessionButton = remember { MutableInteractionSource() }
        val startSessionButtonClick = startSessionButton.collectIsPressedAsState().value

        val finishSessionButton = remember { MutableInteractionSource() }
        val finishSessionButtonClick = finishSessionButton.collectIsPressedAsState().value

        OutlinedButton(
            onClick = cancelButtonClick,
            border = BorderStroke(
                width = 3.dp,
                color =  if(cancelSessionButtonClick) Color(0xFFFF8B00) else Color.Black
            ),

            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = if(cancelSessionButtonClick) Color(0xFFFF8B00) else Color.Black,
            ),

            interactionSource = cancelSessionButton
        ){
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                text = "Cancel"
            )


        }

        OutlinedButton(
            onClick = startButtonClick,

            border = BorderStroke(
                width = 3.dp,
                color =  if(startSessionButtonClick) Color(0xFFFF8B00) else Color.Black
            ),

            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = if(startSessionButtonClick) Color(0xFFFF8B00) else Color.Black,
            ),

            interactionSource = startSessionButton,

            enabled = !sessionTimeRunning
        ){
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                text = "Start"
            )
        }

        OutlinedButton(
            onClick = finishButtonClick,

            border = BorderStroke(
                width = 3.dp,
                color =  if(finishSessionButtonClick) Color(0xFFFF8B00) else Color.Black
            ),

            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = if(finishSessionButtonClick) Color(0xFFFF8B00) else Color.Black,
            ),

            interactionSource = finishSessionButton,
            enabled = sessionTimeRunning
        ){
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                text = "Finish"
            )
        }

    }
}

fun timeString(seconds: Int): String{
    val sessionHour = seconds / 3600
    val sessionMinutes = (seconds % 3600) / 60
    val sessionSeconds = seconds % 60
    return String.format("%02d:%02d:%02d", sessionHour, sessionMinutes, sessionSeconds)
}

fun returnHours(seconds: Int): Int{
    return seconds / 3600
}

fun returnMinutes(seconds: Int): Int{
    return (seconds % 3600) / 60
}

fun returnSeconds(seconds: Int): Int{
    return seconds % 60
}

fun returnNumberOfHours(studyingSessionHour: Int, studyingSessionMinute: Int, studyingSessionSecond: Int): String{
    val numberOfHours = studyingSessionHour + studyingSessionMinute / 60.0 + studyingSessionSecond / 3600.0

    return String.format("%.2f", studyingSessionHour, studyingSessionMinute, studyingSessionSecond, numberOfHours)
}