package com.example.proiectsma

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val subjectId: Int? = null,
    val subjectName: String,
    val studiedHours: Float,
)
