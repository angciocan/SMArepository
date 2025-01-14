package com.example.proiectsma

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {

    @Upsert
    fun addOrUpdateSubject(subject: Subject)

    @Query("SELECT COUNT(*) FROM SUBJECT")
    fun getTotalSubjectsNumber(): Flow<Int>

    @Query("SELECT SUM(studiedHours) FROM SUBJECT")
    fun getTotalStudiedHours(): Flow<Float>

    @Query("SELECT * FROM SUBJECT WHERE subjectId = :subjectId")
    suspend fun getSubjectById(subjectId: Int): Subject?

    @Query("DELETE FROM SUBJECT WHERE subjectId = :subjectId")
    suspend fun deleteSubject(subjectId: Int)

    @Query("SELECT * FROM SUBJECT")
    fun getAllSubjects() : Flow<List<Subject>>
}