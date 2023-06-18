package com.example.hifztracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> studentList;
    private Context context;

    public StudentAdapter(List<Student> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.textViewName.setText(student.getName());
        holder.textViewAge.setText(String.valueOf(student.getAge()));
        holder.textViewClass.setText(student.getStudentClass());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewAge;
        TextView textViewClass;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            textViewClass = itemView.findViewById(R.id.textViewClass);
        }
    }
}

