package com.example.hifztracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextAge, editTextClass, editTextSabaq, editTextSabaqi, editTextManzil;
    private Button buttonAddStudent, buttonRetrieveStudents;
    private TextView textViewStudentRecords;
    private RecyclerView recyclerViewStudents;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextClass = findViewById(R.id.editTextClass);
        editTextSabaq = findViewById(R.id.editTextSabaq);
        editTextSabaqi = findViewById(R.id.editTextSabaqi);
        editTextManzil = findViewById(R.id.editTextManzil);

        buttonAddStudent = findViewById(R.id.buttonAddStudent);
        buttonRetrieveStudents = findViewById(R.id.buttonRetrieveStudents);

        textViewStudentRecords = findViewById(R.id.textViewStudentRecords);

        recyclerViewStudents = findViewById(R.id.recyclerViewStudents);
        recyclerViewStudents.setLayoutManager(new LinearLayoutManager(this));
        studentList = new ArrayList<>();
        studentAdapter = new StudentAdapter(studentList,this);
        recyclerViewStudents.setAdapter(studentAdapter);

        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                int age = Integer.parseInt(editTextAge.getText().toString().trim());
                String studentClass = editTextClass.getText().toString().trim();
                String sabaq = editTextSabaq.getText().toString().trim();
                String sabaqi = editTextSabaqi.getText().toString().trim();
                int currentManzil = Integer.parseInt(editTextManzil.getText().toString().trim());

                Student student = new Student(name, age, studentClass, sabaq, sabaqi, currentManzil);
                studentList.add(student);
                studentAdapter.notifyDataSetChanged();
            }
        });

        buttonRetrieveStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder records = new StringBuilder();
                for (Student student : studentList) {
                    records.append("Name: ").append(student.getName()).append("\n");
                    records.append("Age: ").append(student.getAge()).append("\n");
                    records.append("Class: ").append(student.getStudentClass()).append("\n");
                    records.append("Sabaq: ").append(student.getSabaq()).append("\n");
                    records.append("Sabaqi: ").append(student.getSabaqi()).append("\n");
                    records.append("Manzil: ").append(student.getCurrentManzil()).append("\n");
                    records.append("--------------------------\n");
                }
                textViewStudentRecords.setText(records.toString());
            }
        });
    }
}
