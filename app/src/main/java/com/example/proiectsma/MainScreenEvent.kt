package com.example.proiectsma

sealed class MainScreenEvent {

    data object saveSubject: MainScreenEvent()

    data object deleteSubject: MainScreenEvent()

    data class onDeleteSessionClick(val session: Session): MainScreenEvent()

    data class onAssignmentIsDone(val assignment: Assignment): MainScreenEvent()

    data class onSubjectNameIsChanged(val subjectName: String): MainScreenEvent()

    data class onStudyHoursIsChanged(val numberOfHours: String): MainScreenEvent()
}