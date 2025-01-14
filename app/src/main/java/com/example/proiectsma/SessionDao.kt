package com.example.proiectsma

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface SessionDao {

    @Insert
    suspend fun insertASession(session: Session)

    @Delete
    suspend fun deleteASession(session: Session)

    @Query("SELECT * FROM SESSION")
    fun getAllSession(): Flow<List<Session>>

    @Query("SELECT * FROM SESSION WHERE SessionSubjectId = :subjectId")
    fun getASessionByASubject(subjectId: Int): Flow<List<Session>>

    @Query("SELECT SUM(SessionDuration) FROM SESSION")
    fun getTheTotalSessionTime(): Flow<Long>

    @Query("SELECT SUM(SessionDuration) FROM SESSION WHERE SessionSubjectId = :subjectId")
    fun getTheTotalSessionTimeByASubject(subjectId: Int): Flow<Long>

    @Query("DELETE FROM SESSION WHERE SessionSubjectId = :subjectId")
    fun deleteASessionBySubject(subjectId: Int)
}