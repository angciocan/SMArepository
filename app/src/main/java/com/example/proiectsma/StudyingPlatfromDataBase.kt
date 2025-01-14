package com.example.proiectsma

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proiectsma.Repository.AssignmentRepositoryInterface
import com.example.proiectsma.Repository.SubjectRepositoryInterface
import com.example.proiectsma.RepositoryImplementation.AssignmentRepository
import com.example.proiectsma.RepositoryImplementation.SessionRepository
import com.example.proiectsma.RepositoryImplementation.SubjectRepository
import com.google.firebase.firestore.FirebaseFirestore

@Database(
    entities = [Subject::class, Session::class, Assignment::class],
    version = 1
)

abstract class StudyingPlatformDataBase: RoomDatabase(){
     abstract fun subjectFunctionDao(): SubjectDao

     abstract fun sessionFunctionDao(): SessionDao

     abstract fun assignmentFunctionDao(): AssignmentDao
}

abstract class StudyingPlatformRepositoryModule{
    abstract fun getSubjectRepository(
        implementation: SubjectRepository
    ): SubjectRepositoryInterface

    abstract fun getSessionRepository(
        implementation: SessionRepository
    ): SubjectRepositoryInterface

    abstract fun getAssignmentRepository(
        implementation: AssignmentRepository
    ): AssignmentRepositoryInterface
}

object StudyingPlatformDataBaseModule{

    fun getDataBase(
        studyingApplication: Application
    ): StudyingPlatformDataBase{
        return Room.databaseBuilder(
            studyingApplication,
            StudyingPlatformDataBase :: class.java,
            "studyingPlatform_dataBase"
        ).build()
    }

    fun getFirestoreDb(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    fun getSubjectDao(database: StudyingPlatformDataBase) : SubjectDao{
        return database.subjectFunctionDao()
    }

    fun getSessionDao(database: StudyingPlatformDataBase) : SessionDao{
        return database.sessionFunctionDao()
    }

    fun getAssignmentDao(database: StudyingPlatformDataBase) : AssignmentDao{
        return database.assignmentFunctionDao()
    }
}

