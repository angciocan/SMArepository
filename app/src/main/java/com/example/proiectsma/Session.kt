package com.example.proiectsma

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Session(
    @PrimaryKey(autoGenerate = true)
    val SessionId: Int,
    val SessionSubjectId: Int,
    val SessionDate: Long,
    val SessionDuration: Long,
    val SessionSubject: String
)
