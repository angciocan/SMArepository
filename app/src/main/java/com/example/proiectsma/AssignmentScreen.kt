@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.proiectsma

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssignmentScreen(
    navigation: NavController
){

    val subject = listOf(
        Subject(subjectId = 0, subjectName = "Math", studiedHours = 11f),
        Subject(subjectId = 0, subjectName = "Physics", studiedHours = 15f),
        Subject(subjectId = 0, subjectName = "History", studiedHours = 8f),
        Subject(subjectId = 0, subjectName = "Science", studiedHours = 15f),
        Subject(subjectId = 0, subjectName = "Programming", studiedHours = 9f),
        Subject(subjectId = 0, subjectName = "Electronics", studiedHours = 9f)
    )

    var assignmmentName = remember{
        mutableStateOf("")
    }

    var assignmentRequiments = remember{
        mutableStateOf("")
    }

    var assigmentNameError = rememberSaveable{
        mutableStateOf<String?>(null)
    }

    val subjectListScope = rememberCoroutineScope()

    assigmentNameError.value = when{
        assignmmentName.value.isBlank() -> "The assignment name must not be empty"
        assignmmentName.value.length > 18 -> "The assignment name is too long"
        else -> null
    }

    var isDeleteDialogOpen = rememberSaveable { mutableStateOf(false) }

    var isAssignmentCalendarState = rememberSaveable { mutableStateOf(false) }
    var assignmentCalendarState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )

    val subjectSheetState = rememberModalBottomSheetState()
    var isButtonSubjectSheet = remember{
        mutableStateOf(false)
    }

    val dueDate = rememberSaveable { mutableStateOf("") }
    dueDate.value = assignmentCalendarState.selectedDateMillis.changeTimeToString()

    DeleteAssignmentDialog(
        isActive = isDeleteDialogOpen.value,
        DialogTitle = "Delete assignment",
        Text = "Are you sure to delete this assignment?",
        onDismissButtonClick = { isDeleteDialogOpen.value = false },
        onConfirmButtonClick = { isDeleteDialogOpen.value = false }
    )

    AssignmentCalendar(
        calendarState = assignmentCalendarState,
        calendarOpen = isAssignmentCalendarState.value,
        dismissButtonClick = { isAssignmentCalendarState.value = false },
        confirmButtonClick = { isAssignmentCalendarState.value = false }
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

    Scaffold (
        topBar = {
           AssignmentUpScreen(
               isAssignment = true,
               isDone = false,
               priorityBorderColor = Color(0xFFFF8B00),
               onExitButtonClick = {},
               onDeleteButtonClick = { isDeleteDialogOpen.value = true },
               onPriorityButtonClick = {},
               navigation = navigation
           )
        }
    ){ paddingValue ->
        Column(
           modifier = Modifier
               .verticalScroll(state = rememberScrollState())
               .fillMaxSize()
               .padding(paddingValue)
               .padding(horizontal = 12.dp)
        ){
            OutlinedTextField(
                value = assignmmentName.value,
                onValueChange = { },
                label = {
                    Text(
                        text = "Assignment name",
                        fontWeight = FontWeight.Bold
                    )
                },

                singleLine = true,

                modifier = Modifier.fillMaxWidth().offset(y = 0.dp),

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedLabelColor = Color.Black,
                    cursorColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = assignmentRequiments.value,
                onValueChange = { },
                label = {
                    Text(
                        text = "Assignment requiments",
                        fontWeight = FontWeight.Bold
                    )
                },

                singleLine = true,

                modifier = Modifier.fillMaxWidth().offset(y = 0.dp),

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedLabelColor = Color.Black,
                    cursorColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Due date",
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = dueDate.value,
                    fontWeight = FontWeight.Bold
                )

                IconButton(
                    onClick = { isAssignmentCalendarState.value = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select a due date")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Choose a priority",
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                AssignmentPriority.entries.forEach{
                    assignmentPriority ->
                    AssignmentPriorityButton(
                         modifier = Modifier.weight(1f),
                         content = assignmentPriority.priorityType,
                         //backgroudColor = assignmentPriority.priorityColor,
                         borderColor = if(assignmentPriority == AssignmentPriority.SECONDARY){
                            assignmentPriority.priorityColor
                         } else assignmentPriority.priorityColor,
                         contentColor = if(assignmentPriority == AssignmentPriority.SECONDARY){
                            assignmentPriority.priorityColor
                         } else assignmentPriority.priorityColor,
                         onClick = {}
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "For subject",
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Math",
                    //textSize = Modifier.size(14.dp)
                )

                IconButton(
                    onClick = { isButtonSubjectSheet.value = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Choose a subject")
                }
            }

            val addAssignmentButton = remember { MutableInteractionSource() }
            val addAssignmentButtonClick =addAssignmentButton.collectIsPressedAsState().value

            OutlinedButton(

                onClick = {
                },

                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),

                border = BorderStroke(
                    width = 3.dp,
                    color =  if(addAssignmentButtonClick) Color(0xFFFF8B00) else Color.Black
                ),

                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = if(addAssignmentButtonClick) Color(0xFFFF8B00) else Color.Black,
                ),

                interactionSource = addAssignmentButton

            ) {
                Text(text = "Add the assignment")
            }

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssignmentUpScreen(
    isAssignment: Boolean,
    isDone: Boolean,
    priorityBorderColor: Color,
    onExitButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    onPriorityButtonClick: () -> Unit,
    navigation: NavController
){
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {navigation.navigate("studyingPlatformSubjectScreen")}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Exit from assignment screen"
                )
            }
        },

        title = {
            Text(text = "Assignment")
        },

        actions = {
            if(isAssignment){
                AssignmentCheckBox(
                    assignmentComplete = isDone,
                    borderColor = priorityBorderColor,
                    onCheckBoxClick = onPriorityButtonClick
                )

                IconButton(onClick = onDeleteButtonClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete assignment"
                    )
                }
            }
        }

    )
}

@Composable
fun AssignmentPriorityButton(
    modifier: Modifier,
    content: String,
    borderColor: Color,
    contentColor: Color,
    onClick: () -> Unit
){
    OutlinedButton(

        onClick = {
            onClick()
        },

        modifier = modifier.fillMaxWidth(),

        border = BorderStroke(
            width = 3.dp,
            color = borderColor
        ),

        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.Black,

        ),
    ) {
        Text(text = content,
            color = contentColor)
    }
}

@Composable
fun AssignmentCalendar(
     calendarState: DatePickerState,
     calendarOpen: Boolean,
     okDateText: String = "OK",
     notOkDateText: String = "Cancel",
     dismissButtonClick: () -> Unit,
     confirmButtonClick: () -> Unit,
){
    if(calendarOpen) {
        DatePickerDialog(
            onDismissRequest = dismissButtonClick,
            confirmButton = {
                OutlinedButton(
                    onClick = confirmButtonClick,
                    border = BorderStroke(
                        width = 3.dp,
                        color = Color.Black
                    ),

                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.Black
                    ),
                ) {
                    Text(text = okDateText)
                }

                OutlinedButton(
                    onClick = dismissButtonClick,
                    border = BorderStroke(
                        width = 3.dp,
                        color = Color.Black
                    ),

                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.Black
                    ),
                ) {
                    Text(text = notOkDateText)
                }
            },

            content = {
                DatePicker(
                    state = calendarState,
                    modifier = Modifier.background(color = Color.Gray)
                )
            }
        )
    }
}

fun Long?.changeTimeToString(): String{
    val date: LocalDate = this?.let{
        Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
    } ?: LocalDate.now()

    return date.format(DateTimeFormatter.ofPattern("dd MM yyyy"))
}