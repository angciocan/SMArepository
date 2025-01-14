package com.example.proiectsma


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SubjectDialog(
     isActive: Boolean,
     subjectDialogTitle: String = "Add or edit subject",
     subjectName: String,
     estimatedNumberOfHours: String,

     onSubjectNameUpdate: (String) -> Unit,
     onEstimatedNumberOfHours: (String) -> Unit,
     onDismissButtonClick: () -> Unit,
     onConfirmButtonClick: () -> Unit
){

    var subjectNameError = rememberSaveable { mutableStateOf<String?>(null) }
    var estimatedNumberOfHoursError = rememberSaveable { mutableStateOf<String?>(null) }

    subjectNameError.value = when{
        subjectName.isBlank() -> "The subject name must not be empty"
        subjectName.length > 15 -> "The subject name is too long. Please type a shorter subject name"
        else -> null
    }

    if(isActive) {
        AlertDialog(
            onDismissRequest = onDismissButtonClick,

            icon = {
                Image(
                    painter = painterResource(id = R.drawable.studybooks),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.offset(y = (0).dp).size(80.dp)
                )
            },

            title = {
                Text(text = subjectDialogTitle)
            },

            text = {
                Column{
                  Row(
                      modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                      horizontalArrangement = Arrangement.SpaceAround,

                  ){

                  }

                    OutlinedTextField(
                        value = subjectName,
                        onValueChange = onSubjectNameUpdate,
                        label = {
                            Text(text = "Subject name")
                        },

                        singleLine = true,

                        supportingText = {
                            Text(
                                text = subjectNameError.value.orEmpty(),
                                color = Color.Black
                            )
                        },

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            unfocusedLabelColor = Color.Black,
                            cursorColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = estimatedNumberOfHours,
                        onValueChange = onEstimatedNumberOfHours,
                        label = {
                            Text(text = "Estimated number of hours")
                        },

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            unfocusedLabelColor = Color.Black,
                            cursorColor = Color.Black
                        ),

                        singleLine = true,
                    )
                }
            },

            dismissButton = {
                OutlinedButton(

                    onClick = onDismissButtonClick,

                    border = BorderStroke(
                        width = 2.dp,
                        color = Color.Black
                    ),

                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.Black
                    ),
                ) {
                    Text(text = "Cancel")
                }
            },

            confirmButton = {
                OutlinedButton(

                    onClick = onConfirmButtonClick,

                    border = BorderStroke(
                        width = 2.dp,
                        color = Color.Black
                    ),

                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.Black
                    ),
                ) {
                    Text(text = "Save")
                }
            },

            containerColor = Color(0xFF008BFF),

            shape = RoundedCornerShape(16.dp),
            //modifier = Modifier.border(3.dp, Color.Black, RoundedCornerShape(16.dp))
        )
    }
}