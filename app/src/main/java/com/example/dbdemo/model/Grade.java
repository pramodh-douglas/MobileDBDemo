package com.example.dbdemo.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

// primary key names should match the values you use in the attributes below
@Entity(tableName = "grades",
        primaryKeys = {"courseid", "studentid"},
        // map foreign key from parent table to child table
        foreignKeys = @ForeignKey(entity = Student.class, parentColumns = "studentid", childColumns = "studentid",
        onDelete = ForeignKey.CASCADE), indices = {@Index("studentid")}) // RESTRICT - can delete student record if only every related reacord is removed from grades table
public class Grade {
    @NonNull
    @ColumnInfo(name = "courseid")
    private String CourseId;

    @NonNull
    @ColumnInfo(name = "studentid")
    private String StudentId;

    @NonNull
    @ColumnInfo(name = "studgrade")
    private double StudGrade;

    @NonNull
    public String getCourseId() {
        return CourseId;
    }

    public void setCourseId(@NonNull String courseId) {
        CourseId = courseId;
    }

    @NonNull
    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(@NonNull String studentId) {
        StudentId = studentId;
    }

    public double getStudGrade() {
        return StudGrade;
    }

    public void setStudGrade(double studGrade) {
        StudGrade = studGrade;
    }

    public Grade() {
    }

    public Grade(@NonNull String courseId, @NonNull String studentId, double studGrade) {
        CourseId = courseId;
        StudentId = studentId;
        StudGrade = studGrade;
    }
}
