package com.example.proiectsma

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Recording")
class Recording(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val duration: Int,
    val date: Long
)