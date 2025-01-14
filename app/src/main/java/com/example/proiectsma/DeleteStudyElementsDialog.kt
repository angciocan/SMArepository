package com.example.proiectsma

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun DeleteSubjectDialog(
    isActive: Boolean,
    DialogTitle: String,
    Text: String,
    onDismissButtonClick: () -> Unit,
    onConfirmButtonClick: () -> Unit
){


    if(isActive) {
        AlertDialog(
            onDismissRequest = onDismissButtonClick,

            icon = {
                Image(
                    painter = painterResource(id = R.drawable.studybooks),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.offset(y = (0).dp).size(150.dp)
                )
            },

            title = {
                Text(text = DialogTitle)
            },

            text = {
                Text(text = Text)
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
                    Text(text = "Delete")
                }
            },

            shape = RoundedCornerShape(16.dp),
        )
    }
}

@Composable
fun DeleteAssignmentDialog(
    isActive: Boolean,
    DialogTitle: String,
    Text: String,
    onDismissButtonClick: () -> Unit,
    onConfirmButtonClick: () -> Unit
){


    if(isActive) {
        AlertDialog(
            onDismissRequest = onDismissButtonClick,

            icon = {
                Image(
                    painter = painterResource(id = R.drawable.assignment),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.offset(y = (0).dp).size(150.dp)
                )
            },

            title = {
                Text(text = DialogTitle)
            },

            text = {
                Text(text = Text)
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
                    Text(text = "Delete")
                }
            },

            shape = RoundedCornerShape(16.dp),
        )
    }
}

@Composable
fun DeleteSessionDialog(
    isActive: Boolean,
    DialogTitle: String,
    Text: String,
    onDismissButtonClick: () -> Unit,
    onConfirmButtonClick: () -> Unit
){


    if(isActive) {
        AlertDialog(
            onDismissRequest = onDismissButtonClick,

            icon = {
                Image(
                    painter = painterResource(id = R.drawable.sessionclock),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.offset(y = (0).dp).size(150.dp)
                )
            },

            title = {
                Text(text = DialogTitle)
            },

            text = {
                Text(text = Text)
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
                    Text(text = "Delete")
                }
            },

            shape = RoundedCornerShape(16.dp),
        )
    }
}