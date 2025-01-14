package com.example.proiectsma.Repository

import com.example.proiectsma.Assignment
import kotlinx.coroutines.flow.Flow

interface AssignmentRepositoryInterface {

    suspend fun addOrUpdateAssignment(assignment: Assignment)

    suspend fun deleteAssignment(assignmentId: Int)

    suspend fun deleteAssignmentForASubject(subjectId: Int)

    suspend fun getAnAssignmentByAnId(assignmentId: Int): Assignment?

    fun getAssignmentsForASubject(subjectId: Int): Flow<List<Assignment>>

    fun getAllAssignments(): Flow<List<Assignment>>
}