package com.example.dbdemo.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dbdemo.model.Student;

import java.util.List;

@Dao
public interface StudentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertStudents(Student... students);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertStudentsFromList(List<Student> Students);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOneStudent(Student student);

    @Query("SELECT * FROM students")
    List<Student> GetAllStudents();

    @Delete
    int deleteOneStudent(Student student);

    @Delete
    int deleteStudentsFromList(List<Student> students); // returns deleted number of records

    @Query("DELETE FROM students")
    void deleteAllStudents();

    @Query("DELETE FROM students WHERE studentid = :StudId")
    int deleteStudentWithId(String StudId);
}
