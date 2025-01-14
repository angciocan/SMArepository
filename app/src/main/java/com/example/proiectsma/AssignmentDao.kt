package com.example.proiectsma

import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

interface AssignmentDao {

    @Upsert
    suspend fun addOrUpdateAssignment(assignment: Assignment)

    @Query("DELETE FROM ASSIGNMENT WHERE assignmentId = :assignmentId ")
    suspend fun deleteAssignment(assignmentId: Int)

    @Query("DELETE FROM ASSIGNMENT WHERE assignmentSubjectId = :subjectId ")
    suspend fun deleteAssignmentForASubject(subjectId: Int)

    @Query("SELECT * FROM ASSIGNMENT WHERE assignmentId = :assignmentId")
    suspend fun getAnAssignmentByAnId(assignmentId: Int): Assignment?

    @Query("SELECT * FROM ASSIGNMENT WHERE assignmentSubjectId = :subjectId")
    fun getAssignmentsForASubject(subjectId: Int): Flow<List<Assignment>>

    @Query("SELECT * FROM ASSIGNMENT")
    fun getAllAssignments(): Flow<List<Assignment>>
}