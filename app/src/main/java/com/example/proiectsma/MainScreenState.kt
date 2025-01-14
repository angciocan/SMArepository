package com.example.proiectsma

data class MainScreenState (
    val totalNumberOfSubjects: Int = 0,
    val totalNumberOfHours: Float = 0f,
    val totalNumberOfEstimatedHours: Float = 0f,
    val listOfSubjects:List<Subject> = emptyList(),
    val subjectName: String = "",
    val EstimatedHours: String = "",
    val Session: Session? = null
)