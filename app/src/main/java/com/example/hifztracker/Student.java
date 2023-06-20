package com.example.hifztracker;

public class Student {
    private int Rollno;
    private String name;
    private int age;
    private String studentClass;
    private String sabaq;
    private String sabaqi;
    private int currentManzil;

    public Student(int RollNo, String name, int age, String studentClass) {
        this.Rollno= RollNo;
        this.name = name;
        this.age = age;
        this.studentClass = studentClass;

    }
    public Student(int Rollno, String name, int age, String studentClass, String sabaq, String sabaqi, int currentManzil) {
        this.Rollno = Rollno;
        this.name = name;
        this.age = age;
        this.studentClass = studentClass;
        this.sabaq = sabaq;
        this.sabaqi = sabaqi;
        this.currentManzil = currentManzil;
    }
    public int getRollNo() {
        return Rollno;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public String getSabaq() {
        return sabaq;
    }

    public String getSabaqi() {
        return sabaqi;
    }

    public int getCurrentManzil() {
        return currentManzil;
    }
}
