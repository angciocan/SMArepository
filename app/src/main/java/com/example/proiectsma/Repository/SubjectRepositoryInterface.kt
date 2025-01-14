package com.example.proiectsma.Repository

import com.example.proiectsma.Subject
import kotlinx.coroutines.flow.Flow

interface SubjectRepositoryInterface {

    suspend fun addOrUpdateSubject(subject: Subject)

    fun getTotalSubjectsNumber(): Flow<Int>

    fun getTotalStudiedHours(): Flow<Float>

    suspend fun getSubjectById(subjectId: Int): Subject?

    suspend fun deleteSubject(subjectId: Int)

    fun getAllSubjects() : Flow<List<Subject>>
}