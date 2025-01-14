package com.example.proiectsma.Repository

import com.example.proiectsma.Session
import kotlinx.coroutines.flow.Flow

interface SessionRepositoryInterface {
    suspend fun insertASession(session: Session)

    suspend fun deleteASession(session: Session)

    fun getAllSession(): Flow<List<Session>>

    fun getASessionByASubject(subjectId: Int): Flow<List<Session>>

    fun getTheTotalSessionTime(): Flow<Long>

    fun getTheTotalSessionTimeByASubject(subjectId: Int): Flow<Long>

    fun deleteASessionBySubject(subjectId: Int)
}