package com.example.proiectsma

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Assignment(
    @PrimaryKey(autoGenerate = true)
    val assignmentId: Int,
    val assignmentTitle: String,
    val assignmentDescription: String,
    val assignmentDueDate: Long,
    val assignmentPriority: Int,
    val assignmentComplete: Boolean,
    val assignmentSubjectId: Int
)
