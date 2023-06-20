package com.example.hifztracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void updateList(List<Student> updatedList) {
        studentList = updatedList;
        notifyDataSetChanged();
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
        holder.txtName.setText("Name: " + student.getName());
        holder.txtRollno.setText("RollNo: " + student.getRollNo());
        holder.txtAge.setText("Age: " + String.valueOf(student.getAge()));
        holder.txtClass.setText("Class: " + student.getStudentClass());
        holder.txtSabaq.setText("Sabaq: " + student.getSabaq());
        holder.txtSabaqi.setText("Sabaqi: " + student.getSabaqi());
        holder.txtManzil.setText("Manzil: " + student.getCurrentManzil());

        // Set other views if needed
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtAge, txtClass,txtSabaq,txtSabaqi,txtManzil,txtRollno;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.textViewName);
            txtRollno = itemView.findViewById(R.id.textViewRollNo);
            txtAge = itemView.findViewById(R.id.textViewAge);
            txtClass = itemView.findViewById(R.id.textViewClass);
            txtSabaq = itemView.findViewById(R.id.textViewSabaq);
            txtSabaqi = itemView.findViewById(R.id.textViewSabaqi);
            txtManzil = itemView.findViewById(R.id.textViewManzil);


            // Find other views if needed
        }
    }
}
