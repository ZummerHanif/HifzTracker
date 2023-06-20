package com.example.hifztracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private Button buttonSearch;
    private RecyclerView recyclerViewSearchResults;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;

    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        databaseHelper = new DatabaseHelper(this);


        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);
        recyclerViewSearchResults = findViewById(R.id.recyclerViewSearchResults);

        studentList = new ArrayList<>();
        studentAdapter = new StudentAdapter(studentList);
        recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSearchResults.setAdapter(studentAdapter);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rollNumber = editTextSearch.getText().toString().trim();
                searchStudent(Integer.parseInt(rollNumber));
            }
        });
    }



    private void searchStudent(int rollNumber) {

        Student searchResults = databaseHelper.getStudent(rollNumber);
        studentList.clear();

        if (searchResults != null) {
            studentList.add(searchResults);
        }


        // Notify the adapter about the data change
        studentAdapter.notifyDataSetChanged();
    }
}

