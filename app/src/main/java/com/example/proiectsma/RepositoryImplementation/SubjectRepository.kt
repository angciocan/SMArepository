package com.example.proiectsma.RepositoryImplementation

import com.example.proiectsma.Repository.SubjectRepositoryInterface
import com.example.proiectsma.Subject
import com.example.proiectsma.SubjectDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubjectRepository @Inject constructor(
    private val subjectDao: SubjectDao
): SubjectRepositoryInterface {
    override suspend fun addOrUpdateSubject(subject: Subject) {
        subjectDao.addOrUpdateSubject(subject)
    }

    override fun getTotalSubjectsNumber(): Flow<Int> {
        return subjectDao.getTotalSubjectsNumber()
    }

    override fun getTotalStudiedHours(): Flow<Float> {
        return subjectDao.getTotalStudiedHours()
    }

    override suspend fun getSubjectById(subjectId: Int): Subject? {
        return subjectDao.getSubjectById(subjectId)
    }

    override suspend fun deleteSubject(subjectId: Int) {
       subjectDao.deleteSubject(subjectId)
    }

    override fun getAllSubjects(): Flow<List<Subject>> {
        return subjectDao.getAllSubjects()
    }

}