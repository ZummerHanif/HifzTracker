package com.example.hifztracker;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hifztracker.DatabaseHelper;
import com.example.hifztracker.R;

public class AddRecordActivity extends AppCompatActivity {
    private EditText editTextRollNo;
    private EditText editTextSabaq;
    private EditText editTextSabaqi;
    private EditText editTextManzil;
    private Button btnSaveRecord;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        dbHelper = new DatabaseHelper(this);

        editTextRollNo = findViewById(R.id.editTextRollNo);
        editTextSabaq = findViewById(R.id.editTextSabaq);
        editTextSabaqi = findViewById(R.id.editTextSabaqi);
        editTextManzil = findViewById(R.id.editTextManzil);
        btnSaveRecord = findViewById(R.id.btnSaveRecord);

        btnSaveRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecord();
            }
        });
    }

    private void addRecord() {
        String rollNoStr = editTextRollNo.getText().toString().trim();
        String sabaq = editTextSabaq.getText().toString().trim();
        String sabaqi = editTextSabaqi.getText().toString().trim();
        String manzil = editTextManzil.getText().toString().trim();

        // Validate input fields
        if (TextUtils.isEmpty(rollNoStr) || TextUtils.isEmpty(sabaq) || TextUtils.isEmpty(sabaqi) || TextUtils.isEmpty(manzil)) {
            Toast.makeText(AddRecordActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int rollNo = Integer.parseInt(rollNoStr);

        // Check if the student with the given roll number exists in the database
        if (!dbHelper.isStudentExists(rollNo)) {
            Toast.makeText(AddRecordActivity.this, "Student with Roll No " + rollNo + " does not exist", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add the record to the database
        int manzilValue = Integer.parseInt(manzil);
        int isRecordAdded = dbHelper.addRecord(rollNo, sabaq, sabaqi, manzilValue);

        if (isRecordAdded != -1) {
            Toast.makeText(AddRecordActivity.this, "Record added successfully", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(AddRecordActivity.this, "Failed to add record", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        editTextRollNo.setText("");
        editTextSabaq.setText("");
        editTextSabaqi.setText("");
        editTextManzil.setText("");
    }
}
