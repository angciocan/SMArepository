package com.example.proiectsma

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Course")
class Course (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val courseName: String
)
