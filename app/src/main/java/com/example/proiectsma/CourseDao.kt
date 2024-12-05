package com.example.proiectsma

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CourseDao {
    @Insert
    fun insert(course: Course) : LiveData<List<Course>>

    @Delete
    fun delete(course: Course) : LiveData<List<Course>>

    @Update
    fun update(course: Course) : LiveData<List<Course>>

    @Query("SELECT * FROM Course")
    fun getAllCourses(): List<Course>
}
