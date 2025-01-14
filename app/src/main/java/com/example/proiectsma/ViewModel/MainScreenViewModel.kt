package com.example.proiectsma.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proiectsma.MainScreenEvent
import com.example.proiectsma.MainScreenState
import com.example.proiectsma.Repository.SessionRepositoryInterface
import com.example.proiectsma.Repository.SubjectRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    val subjectRepository: SubjectRepositoryInterface,
    val sessionRepository: SessionRepositoryInterface
): ViewModel(){
     val mainScreenState = MutableStateFlow(MainScreenState())

     val main_screen_state = combine(
         mainScreenState,
         subjectRepository.getTotalSubjectsNumber(),
         subjectRepository.getTotalStudiedHours(),
         subjectRepository.getAllSubjects(),
         sessionRepository.getTheTotalSessionTime()
     ){
         mainScreenState, subjectNumber, studiedHours, allSubjects, totalSessionTime ->
         mainScreenState.copy(
             totalNumberOfSubjects = subjectNumber,
             totalNumberOfEstimatedHours = studiedHours,
             listOfSubjects = allSubjects,
             totalNumberOfHours = totalSessionTime.convertIntoHours()
         )
     }.stateIn(
        scope = viewModelScope,
         started = SharingStarted.WhileSubscribed(5000),
         initialValue = MainScreenState()
     )

    fun eventFunction(event: MainScreenEvent){
        when(event){
            MainScreenEvent.deleteSubject -> TODO()
            is MainScreenEvent.onAssignmentIsDone -> TODO()
            is MainScreenEvent.onDeleteSessionClick -> TODO()
            is MainScreenEvent.onStudyHoursIsChanged -> TODO()
            is MainScreenEvent.onSubjectNameIsChanged -> TODO()
            MainScreenEvent.saveSubject -> TODO()
        }
    }
}

fun Long.convertIntoHours(): Float{
    val hoursList = this.toFloat() / 3600f
    return "%.2f".format(hoursList).toFloat()
}