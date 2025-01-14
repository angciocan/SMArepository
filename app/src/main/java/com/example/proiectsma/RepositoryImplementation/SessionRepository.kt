package com.example.proiectsma.RepositoryImplementation

import com.example.proiectsma.Repository.SessionRepositoryInterface
import com.example.proiectsma.Session
import com.example.proiectsma.SessionDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SessionRepository @Inject constructor(
    private val sessionDao: SessionDao
): SessionRepositoryInterface {
    override suspend fun insertASession(session: Session) {
        sessionDao.insertASession(session)
    }

    override suspend fun deleteASession(session: Session) {
        sessionDao.deleteASession(session)
    }

    override fun getAllSession(): Flow<List<Session>> {
        return sessionDao.getAllSession()
    }

    override fun getASessionByASubject(subjectId: Int): Flow<List<Session>> {
        return sessionDao.getASessionByASubject(subjectId)
    }

    override fun getTheTotalSessionTime(): Flow<Long> {
        return sessionDao.getTheTotalSessionTime()
    }

    override fun getTheTotalSessionTimeByASubject(subjectId: Int): Flow<Long> {
        return sessionDao.getTheTotalSessionTimeByASubject(subjectId)
    }

    override fun deleteASessionBySubject(subjectId: Int) {
        sessionDao.deleteASessionBySubject(subjectId)
    }

}