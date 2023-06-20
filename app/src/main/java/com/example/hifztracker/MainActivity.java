package com.example.hifztracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextAge, editTextClass,editTextRollNo;
    private Button btnAddStudent, btnAddRecord, btnSearch;
    private RecyclerView recyclerViewStudents;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;
    private DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        btnAddStudent = findViewById(R.id.btnAddStudent);
        btnAddRecord = findViewById(R.id.btnAddRecord);
        btnSearch = findViewById(R.id.btnSearch);
        recyclerViewStudents = findViewById(R.id.recyclerViewStudents);
        editTextName= findViewById(R.id.editTextName);
        editTextAge= findViewById(R.id.editTextAge);
        editTextClass = findViewById(R.id.editTextClass);
        editTextRollNo = findViewById(R.id.editTextRollNo);
        recyclerViewStudents = findViewById(R.id.recyclerViewStudents);


        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);


        // Set click listeners
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddStudentActivity();
            }
        });

        btnAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddRecordActivity();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchActivity();
            }
        });

        // Set up RecyclerView
        studentList = new ArrayList<>();
        studentAdapter = new StudentAdapter(studentList);
        recyclerViewStudents.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewStudents.setAdapter(studentAdapter);
    }
    private void openAddStudentActivity() {
        String name = editTextName.getText().toString().trim();
        int age = Integer.parseInt(editTextAge.getText().toString().trim());
        String studentClass = editTextClass.getText().toString().trim();
        int Rollno= Integer.parseInt(editTextRollNo.getText().toString().trim());

        // Validate input fields
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(studentClass)) {
            Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new Student object
        Student student = new Student(Rollno,name, age, studentClass);

        // Add the student to the database
        long studentId = databaseHelper.addStudent(student);

        if (studentId != -1) {
            Toast.makeText(MainActivity.this, "Student added successfully", Toast.LENGTH_SHORT).show();
            clearFields();
            loadStudents();
        } else {
            Toast.makeText(MainActivity.this, "Failed to add student", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        editTextName.setText("");
        editTextAge.setText("");
        editTextClass.setText("");
        editTextRollNo.setText("");
    }

    private void loadStudents() {
        studentList.clear();
        studentList.addAll(databaseHelper.getAllStudents());
        studentAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveStudents();
    }

    private void retrieveStudents() {
        // Retrieve all students from the database
        studentList = databaseHelper.getAllStudents();

        // Update the RecyclerView with the retrieved student records
        studentAdapter.updateList(studentList);
    }



    private void openAddRecordActivity() {
        Intent intent = new Intent(MainActivity.this, AddRecordActivity.class);
        startActivity(intent);
    }

    private void openSearchActivity() {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }
}
