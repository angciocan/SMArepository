package com.example.proiectsma

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyingPlatformSubjectScreen(
    navigation: NavController
){

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    var isAddSubjectDialogOpen = rememberSaveable {mutableStateOf(false) }
    var isDeleteSubjectDialogOpen = rememberSaveable {mutableStateOf(false) }
    var isEditSubjectDialogOpen = rememberSaveable {mutableStateOf(false) }

    var subjectName = remember { mutableStateOf("") }

    var estimatedNumberOfHours = remember { mutableStateOf("") }

    val assignments = listOf(
        Assignment(
            assignmentId = 1,
            assignmentTitle = "Assignment1",
            assignmentDescription = "",
            assignmentDueDate = 0L,
            assignmentPriority = 1,
            assignmentComplete = true,
            assignmentSubjectId = 1,
        ),

        Assignment(
            assignmentId = 1,
            assignmentTitle = "Assignment1",
            assignmentDescription = "",
            assignmentDueDate = 0L,
            assignmentPriority = 2,
            assignmentComplete = true,
            assignmentSubjectId = 1,
        ),

        Assignment(
            assignmentId = 1,
            assignmentTitle = "Assignment1",
            assignmentDescription = "",
            assignmentDueDate = 0L,
            assignmentPriority = 1,
            assignmentComplete = false,
            assignmentSubjectId = 1,
        ),

        Assignment(
            assignmentId = 1,
            assignmentTitle = "Assignment1",
            assignmentDescription = "",
            assignmentDueDate = 0L,
            assignmentPriority = 2,
            assignmentComplete = true,
            assignmentSubjectId = 1,
        ),

        Assignment(
            assignmentId = 1,
            assignmentTitle = "Assignment1",
            assignmentDescription = "",
            assignmentDueDate = 0L,
            assignmentPriority = 1,
            assignmentComplete = true,
            assignmentSubjectId = 1,
        ),
    )

    SubjectDialog(
        isActive = isAddSubjectDialogOpen.value,
        subjectName = subjectName.value,
        estimatedNumberOfHours = estimatedNumberOfHours.value,
        onSubjectNameUpdate = { subjectName.value = it},
        onEstimatedNumberOfHours = {estimatedNumberOfHours.value = it},
        onDismissButtonClick = { isAddSubjectDialogOpen.value = false},
        onConfirmButtonClick = {
            isAddSubjectDialogOpen.value = false
        }
    )

    DeleteSubjectDialog(
        isActive = isDeleteSubjectDialogOpen.value,
        DialogTitle = "Delete subject",
        Text = "Are you sure to delete this subject?",
        onDismissButtonClick = { isDeleteSubjectDialogOpen.value = false },
        onConfirmButtonClick = { isDeleteSubjectDialogOpen.value = false }
    )

    Scaffold(

        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {

            SubjectUpScreen(
               subjectTitle = "Math",
                onSubjectsScreenClick = {},
                onDeleteSubjectClick = { isDeleteSubjectDialogOpen.value = true },
                onEditSubjectClick = { isAddSubjectDialogOpen.value = true },
                scrollBehavior = scrollBehavior,
                navigation = navigation
            )
        },

        floatingActionButton = {

            Button(
                onClick = { navigation.navigate("assignmentScreen")},
                border = BorderStroke(
                    width = 3.dp,
                    color =  Color.Black
                ),

               colors = ButtonDefaults.buttonColors(
                   containerColor = Color(0xFFFF8B00)
               )
            ){
                Text(
                    text = "Add assignment",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Icon(imageVector = Icons.Default.Add, contentDescription = "Add assignment", tint = Color.Black)
            }
        }
    ){
        paddingValue->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValue)
        ){
             item{
                 SubjectInformations(
                     modifier = Modifier.fillMaxWidth().padding(12.dp),
                     numberOfHours = "18",
                     estimatedNumberOfHours = "20",
                     progress = 0.60f
                 )
             }

            AssignmentList(
                AssignmentTitle = "Next assignments",
                AssignmentEmptyListText = "There are not assignments at this moment!",
                AssignmentList = assignments,
                onCheckBoxClick = {},
                onAssignmentObjectClick = {}
            )

            item{
                Spacer(modifier = Modifier.height(20.dp))
            }

            AssignmentList(
                AssignmentTitle = "Done assignments",
                AssignmentEmptyListText = "There are not assignments at this moment!",
                AssignmentList = assignments,
                onCheckBoxClick = {},
                onAssignmentObjectClick = {}
            )

            item{
                Spacer(modifier = Modifier.height(20.dp))
            }

            SessionList(
                SessionTitle = "Actual study sessions",
                SessionEmptyListText = "There are not sessions at this moment!",
                SessionList = emptyList(),
                onDeleteSessionClick = { isDeleteSubjectDialogOpen.value = true }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectUpScreen(
    subjectTitle: String,
    onSubjectsScreenClick: () -> Unit,
    onDeleteSubjectClick: () -> Unit,
    onEditSubjectClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    navigation: NavController
){
    LargeTopAppBar(

        scrollBehavior = scrollBehavior,
        navigationIcon = {
           IconButton(onClick = {navigation.navigate("studyingPlatformSubjectsScreen")}){
               Icon(
                   imageVector = Icons.Default.ArrowBack,
                   contentDescription = "To subjects screen"
               )
           }
        },

        title = {
            Text(
                text = subjectTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },

        actions = {
            IconButton(onClick = onDeleteSubjectClick){
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete subject",
                    tint = Color(0xFFFF8B00)
                )
            }

            IconButton(onClick = onEditSubjectClick){
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit subject",
                    tint = Color.Gray
                )
            }
        }
    )
}

@Composable
fun SubjectInformations(
    modifier: Modifier,
    numberOfHours: String,
    estimatedNumberOfHours: String,
    progress: Float
){
    val percentProgress = remember(progress){
        (progress * 100).toInt().coerceIn(0, 100)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ){
       InformativeSection(
           modifier = Modifier.weight(1f),
           informativeText = "Estimated studied hours",
           number = "15"
       )

        Spacer(modifier = Modifier.width(5.dp))

        InformativeSection(
            modifier = Modifier.weight(1f),
            informativeText = "Number of hours",
            number = "20"
        )

        Spacer(modifier = Modifier.width(5.dp))

        Box(
            modifier = Modifier.size(100.dp),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                strokeWidth = 3.dp,
                progress = 1f,
                strokeCap = StrokeCap.Round,
                color = Color(0xFFFF8B00)
            )

            Text(
                text = "$percentProgress%",
                fontWeight = FontWeight.Bold
            )
        }
    }

}