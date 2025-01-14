package com.example.proiectsma

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyingPlatformSubjectsScreen(
    navigation: NavController,
    onSubjectObjectClick: (Int?) -> Unit,
    onAssignmentObjectClick: (Int?) -> Unit,
    onStartSessionClick: (Int?) -> Unit
    ){

    var isSubjectDialogOpen = rememberSaveable { mutableStateOf(false) }
    var isSessionDialogOpen = rememberSaveable { mutableStateOf(false) }

    var subjectName = remember { mutableStateOf("") }

    var estimatedNumberOfHours = remember { mutableStateOf("") }

//    val mainScreenViewModel: MainScreenViewModel = hiltViewModel()
//
//    val mainScreenState by mainScreenViewModel.mainScreenState.collectAsStateWithLifecycle()

    val Subjects = listOf(
        Subject(subjectId = 1, subjectName = "Math", studiedHours = 12.9f)
    )

    SubjectDialog(
        isActive = isSubjectDialogOpen.value,
        subjectName = subjectName.value,
        estimatedNumberOfHours = estimatedNumberOfHours.value,
        onSubjectNameUpdate = { },
        onEstimatedNumberOfHours = { },
        onDismissButtonClick = { isSubjectDialogOpen.value = false},
        onConfirmButtonClick = {
            isSubjectDialogOpen.value = false
        }
    )

    DeleteSessionDialog(
        isActive = isSessionDialogOpen.value,
        DialogTitle = "Delete session",
        Text = "Are you sure to delete this session?",
        onDismissButtonClick = { isSessionDialogOpen.value = false },
        onConfirmButtonClick = { isSessionDialogOpen.value = false }
    )

    Scaffold(
         topBar = {
             TopAppBar(
                  title = {
                          Text(
                              text = "Studying Platform",
                              color = Color.Black,
                              modifier = Modifier.offset(x = 30.dp),
                          )
                  },

                 navigationIcon = {
                     Image(
                         painter = painterResource(id = R.drawable.graduationhat),
                         contentDescription = null,
                         contentScale = ContentScale.Fit,
                         modifier = Modifier.offset(x = 280.dp).size(60.dp)
                     )

                     IconButton(onClick = {}){
                         Icon(
                             imageVector = Icons.Default.Menu,
                             contentDescription = "Delete subject",

                             modifier = Modifier.offset(x = 0.dp).size(60.dp)
                         )
                     }
                 },

                 colors = TopAppBarDefaults.mediumTopAppBarColors(
                     containerColor = Color(0xFFFF8B00)
                 ),

                 modifier = Modifier.clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
             )


         },

    ) {
        paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ){
            item{
                InformativePlace(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    numberOfHours = "6",
                    plannedNumberOfStudiedHours = "8"
                )
            }

            item{
                SubjectSection(
                   modifier = Modifier.fillMaxWidth(),
                    SubjectList = Subjects,
                    onAddSubjectButton = {
                        isSubjectDialogOpen.value = true
                    },
                    navigation = navigation
                )
            }

            item{
                val SessionButton = remember { MutableInteractionSource() }
                val SessionButtonClick = SessionButton.collectIsPressedAsState().value

                OutlinedButton(

                    onClick = {
                         navigation.navigate("sessionScreen")
                    },

                    modifier = Modifier.fillMaxWidth().padding(horizontal = 48.dp, vertical = 20.dp),

                    border = BorderStroke(
                        width = 3.dp,
                        color =  if(SessionButtonClick) Color(0xFFFF8B00) else Color.Black
                    ),

                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = if(SessionButtonClick) Color(0xFFFF8B00) else Color.Black,
                    ),

                    interactionSource = SessionButton
                ) {
                    Text(text = "Add a new session")
                }
            }

            SessionList(
                SessionTitle = "Actual study sessions",
                SessionEmptyListText = "There are not sessions at this moment!",
                SessionList = emptyList(),
                onDeleteSessionClick = { isSessionDialogOpen.value = true }
            )

            AssignmentList(
                AssignmentTitle = "Next assignments",
                AssignmentEmptyListText = "There are not assignments at this moment!",
                AssignmentList = emptyList(),
                onCheckBoxClick = {},
                onAssignmentObjectClick = {}
            )
        }
    }
}

@Composable
fun InformativeSection(
    modifier : Modifier = Modifier,
    informativeText: String,
    number: String
){
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFFF8B00)
        )
    ){
       Column(
           modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 12.dp),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
       ){
          Text(
              text = informativeText,
              fontWeight = FontWeight.Bold,
          )

           Spacer(modifier = Modifier.height(5.dp))

           Text(
               text = number,
               textAlign = TextAlign.Center,
               fontWeight = FontWeight.Bold
           )
       }
    }
}

@Composable
fun InformativePlace(
    modifier: Modifier,
    numberOfHours: String,
    plannedNumberOfStudiedHours: String
){
    Row(modifier = modifier
    ){
        InformativeSection(
            modifier = Modifier.weight(2f),
            informativeText = "Study progress in hours",
            number = numberOfHours
        )

        Spacer(modifier = Modifier.width(20.dp))

        InformativeSection(
            modifier = Modifier.weight(2f),
            informativeText = "Estimated studied hours",
            number = plannedNumberOfStudiedHours
        )
    }
}

@Composable
fun SubjectSection(
    modifier: Modifier,
    SubjectList: List<Subject>,
    EmptySubjectText: String = "Click to the add icon to add subjects!",
    onAddSubjectButton:() -> Unit,
    navigation: NavController
){
     Column(
         modifier = modifier.fillMaxSize()
     ){
             Row(
                 modifier = Modifier.fillMaxWidth(),
                 verticalAlignment = Alignment.CenterVertically,
                 horizontalArrangement = Arrangement.SpaceBetween
             ) {
                 Text(
                     text = "Subjects",
                     modifier = Modifier.padding(start = 12.dp),
                     fontWeight = FontWeight.Bold
                 )

                 IconButton( onClick = onAddSubjectButton) {
                   Icon(
                       imageVector = Icons.Default.Add,
                       contentDescription = "Add Subject",
                       tint = Color(0xFFFF8B00)
                   )

                 }
             }

             if(SubjectList.isEmpty()){
                 Text(
                     modifier = Modifier.fillMaxWidth(),
                     text = EmptySubjectText,
                     color = Color.Gray,
                     textAlign = TextAlign.Center,
                     fontWeight = FontWeight.Bold
                 )
             }

             LazyRow(
                 horizontalArrangement = Arrangement.spacedBy(12.dp),
                 contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
             ){
                 items(SubjectList){
                     subject ->
                     SubjectObject(
                         SubjectName = subject.subjectName,
                         StudiedHours = subject.studiedHours,
                         onClick = {},
                         navigation = navigation
                     )
                 }
             }
         }
}

@Composable
fun SubjectObject(
    modifier : Modifier = Modifier,
    SubjectName: String,
    StudiedHours: Float,
    onClick: () -> Unit,
    navigation: NavController
){
    Box(
       modifier = Modifier.size(200.dp).clickable{ navigation.navigate("studyingPlatformSubjectScreen") }.background(
           color = Color.Gray,
           shape = MaterialTheme.shapes.medium,
       )
    ){
       Column(
           modifier = Modifier.padding(12.dp),
           verticalArrangement = Arrangement.Center
       ){
           Text(
               text = SubjectName,
               color = Color.Black,
               fontWeight = FontWeight.Bold
           )

           Image(
               painter = painterResource(id = R.drawable.studybooks),
               contentDescription = null,
               contentScale = ContentScale.Fit,
               modifier = Modifier.offset(y = (0).dp).size(300.dp)
           )
       }
    }
}

fun LazyListScope.AssignmentList(
    AssignmentTitle: String,
    AssignmentEmptyListText: String,
    AssignmentList: List<Assignment>,
    onAssignmentObjectClick: (Int?) -> Unit,
    onCheckBoxClick: (Assignment) -> Unit
){
   item{
       Text(
           text = AssignmentTitle,
           modifier = Modifier.padding(12.dp),
           fontWeight = FontWeight.Bold
       )
   }

    if(AssignmentList.isEmpty()){
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = AssignmentEmptyListText,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    items(AssignmentList){
        assignment ->
        AssignmentObject(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            assignment = assignment,
            onCheckBoxClick = { onCheckBoxClick(assignment) },
            onClick = { onAssignmentObjectClick(assignment.assignmentId) }
        )
    }
}


@Composable
fun AssignmentObject(
    modifier: Modifier = Modifier,
    assignment: Assignment,
    onCheckBoxClick: () -> Unit,
    onClick: () -> Unit
){
  ElevatedCard(
      modifier = modifier.clickable{ }.fillMaxWidth()
          .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
  ){
     Row(
         modifier = Modifier.fillMaxWidth().padding(8.dp),
         verticalAlignment = Alignment.CenterVertically
     ){

         AssignmentCheckBox(
             assignmentComplete = assignment.assignmentComplete,
             borderColor = AssignmentPriority.priority(assignment.assignmentPriority).priorityColor,
             onCheckBoxClick = {}
         )

         Spacer(modifier = Modifier.width(10.dp))

         Column{
             Text(
                 text = assignment.assignmentTitle,
                 maxLines = 1,
                 overflow = TextOverflow.Ellipsis,
                 textDecoration = if(assignment.assignmentComplete){
                     TextDecoration.LineThrough} else TextDecoration.None,

                 fontWeight = FontWeight.Bold
                 )

             Spacer(modifier = Modifier.height(4.dp))

             Text(
                 text = "${assignment.assignmentId}"
             )
         }
     }
  }
}

@Composable
fun AssignmentCheckBox(
    assignmentComplete: Boolean,
    borderColor: Color,
    onCheckBoxClick: () -> Unit,
){
    Box(
        modifier = Modifier.size(25.dp).
        clip(RoundedCornerShape(18.dp)).
        border(2.dp, borderColor, RoundedCornerShape(18.dp)).
        clickable{ onCheckBoxClick() },

        contentAlignment = Alignment.Center
    ){
        AnimatedVisibility(visible = assignmentComplete){
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Rounded.Check,
                contentDescription = null
            )
        }
    }

}





