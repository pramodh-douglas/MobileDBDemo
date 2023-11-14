package com.example.dbdemo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

//import com.example.dbdemo.R;
import com.example.dbdemo.R;
import com.example.dbdemo.adapters.StudentAdapter;
import com.example.dbdemo.database.CollegeDatabase;
import com.example.dbdemo.databinding.ActivityMainBinding;
import com.example.dbdemo.model.Grade;
import com.example.dbdemo.model.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    List<Student> StudentList = new ArrayList<>();
    CollegeDatabase cdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding binding
                = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        StudentList = ReadStudentCSV();
        Log.d("DBDEMO",StudentList.size() + " Students in the file");

        // binding.listViewStudents.setAdapter(new StudentAdapter(StudentList));

        cdb = Room.databaseBuilder(getApplicationContext(), CollegeDatabase.class, "college.db").build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                cdb.studentDAO().insertStudentsFromList(StudentList);
                List<Student> StudentsFromDB = cdb.studentDAO().GetAllStudents();
                List<Grade> GradesFromFile = ReadGrades();

                cdb.gradeDAO().insertGrades(GradesFromFile);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.listViewStudents.setAdapter(new StudentAdapter(StudentsFromDB));
                    }
                });
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NextActivity.class));
            }
        });
    }

    // read grades from csv file and returns grades in list
    private List<Grade> ReadGrades() {
        List<Grade> Grades = new ArrayList<>();
        InputStream inputStream = getResources().openRawResource(R.raw.grades);
        BufferedReader reader = new BufferedReader((new InputStreamReader(inputStream)));
        try {
            String CSVLine;
            if((CSVLine = reader.readLine()) != null) {
                // header line
            }
            while ((CSVLine = reader.readLine()) != null) {
                String[] eachGradeFields = CSVLine.split(",");
                double grade = Double.parseDouble(eachGradeFields[2]);
                Grade eachGrade = new Grade(eachGradeFields[0], eachGradeFields[1], grade);
                Grades.add(eachGrade);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Grades;
    }

    private List<Student> ReadStudentCSV(){
        List<Student> Students = new ArrayList<>();
        InputStream inputStream = getResources()
                                    .openRawResource(R.raw.students);
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(inputStream));
        String studentLine;

        try {
            if ((studentLine = reader.readLine()) != null){
                //process header
            }
            while((studentLine = reader.readLine()) != null){
                String[] eachStudFields = studentLine.split(",");
                Student eachStudent =
                        new Student(eachStudFields[0],
                                eachStudFields[1], eachStudFields[2]);
                Students.add(eachStudent); //cannot add to a null list
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return Students;
    }


}