package com.example.dbdemo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import com.example.dbdemo.R;
import com.example.dbdemo.database.CollegeDatabase;
import com.example.dbdemo.databinding.ActivityNextBinding;
import com.example.dbdemo.model.Grade;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NextActivity extends AppCompatActivity {
    CollegeDatabase cdb;
    ActivityNextBinding binding;
    StringBuilder outputText = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNextBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cdb = Room.databaseBuilder(getApplicationContext(), CollegeDatabase.class, "college.db").build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                outputText.append("Displaying Grades...\n");
                List<Grade> AllGrades = cdb.gradeDAO().GetAllGrades();
                // header line formatting
                outputText.append(String.format("%-10s%-10s%-10s\n", "CourseId", "StudId", "Grade"));
                // inserting data rows while formatting
                for (Grade grade:AllGrades){
                    outputText.append(String.format("%-10s%-10s%-10.2f\n", grade.getCourseId(), grade.getStudentId(), grade.getStudGrade()));
                }
                int retValue = cdb.studentDAO().deleteStudentWithId("312345");
                Log.d("MINE", "retValue = " + retValue);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.txtViewSummary.setText(outputText.toString());
                    }
                });
            }
        });
    }
}