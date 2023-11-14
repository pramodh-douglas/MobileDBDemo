package com.example.dbdemo.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class Student {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "studentid")
    private String StudId;
    @ColumnInfo(name = "studentname")
    private String StudName;
    @ColumnInfo(name = "studentdept")
    private String StudDept;

    @NonNull
    public String getStudId() {
        return StudId;
    }

    public void setStudId(@NonNull String studId) {
        StudId = studId;
    }

    public String getStudName() {
        return StudName;
    }

    public void setStudName(String studName) {
        StudName = studName;
    }

    public String getStudDept() {
        return StudDept;
    }

    public void setStudDept(String studDept) {
        StudDept = studDept;
    }

    public Student() {
    }

    public Student(@NonNull String studId, String studName, String studDept) {
        StudId = studId;
        StudName = studName;
        StudDept = studDept;
    }
}
