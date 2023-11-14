package com.example.dbdemo.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.dbdemo.interfaces.GradeDAO;
import com.example.dbdemo.interfaces.StudentDAO;
import com.example.dbdemo.model.Grade;
import com.example.dbdemo.model.Student;

@Database(entities = {Student.class, Grade.class}, version = 1, exportSchema = false)
public abstract class CollegeDatabase extends RoomDatabase {
    public abstract StudentDAO studentDAO();
    public abstract GradeDAO gradeDAO();

}
