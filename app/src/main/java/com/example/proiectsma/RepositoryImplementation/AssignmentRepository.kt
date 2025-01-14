package com.example.proiectsma.RepositoryImplementation

import com.example.proiectsma.Assignment
import com.example.proiectsma.AssignmentDao
import com.example.proiectsma.Repository.AssignmentRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AssignmentRepository @Inject constructor(
    private val assignmentDao: AssignmentDao
): AssignmentRepositoryInterface {
    override suspend fun addOrUpdateAssignment(assignment: Assignment) {
        assignmentDao.addOrUpdateAssignment(assignment)
    }

    override suspend fun deleteAssignment(assignmentId: Int) {
        assignmentDao.deleteAssignment(assignmentId)
    }

    override suspend fun deleteAssignmentForASubject(subjectId: Int) {
        assignmentDao.deleteAssignmentForASubject(subjectId)
    }

    override suspend fun getAnAssignmentByAnId(assignmentId: Int): Assignment? {
        return assignmentDao.getAnAssignmentByAnId(assignmentId)
    }

    override fun getAssignmentsForASubject(subjectId: Int): Flow<List<Assignment>> {
        return assignmentDao.getAssignmentsForASubject(subjectId)
    }

    override fun getAllAssignments(): Flow<List<Assignment>> {
        return assignmentDao.getAllAssignments()
    }

}