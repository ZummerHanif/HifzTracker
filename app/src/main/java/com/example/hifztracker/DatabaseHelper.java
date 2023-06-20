package com.example.hifztracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student_records.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    private static final String TABLE_STUDENTS = "students";

    // Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_CLASS = "class";
    private static final String COLUMN_SABAQ = "sabaq";
    private static final String COLUMN_SABAQI = "sabaqi";
    private static final String COLUMN_CURRENT_MANZIL = "current_manzil";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_STUDENTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AGE + " INTEGER, " +
                COLUMN_CLASS + " TEXT, " +
                COLUMN_SABAQ + " TEXT, " +
                COLUMN_SABAQI + " TEXT, " +
                COLUMN_CURRENT_MANZIL + " INTEGER" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }

    public long addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_AGE, student.getAge());
        values.put(COLUMN_CLASS, student.getStudentClass());
        values.put(COLUMN_SABAQ, student.getSabaq());
        values.put(COLUMN_SABAQI, student.getSabaqi());
        values.put(COLUMN_CURRENT_MANZIL, student.getCurrentManzil());
        long id = db.insert(TABLE_STUDENTS, null, values);
        db.close();
        return id;
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
                int nameIndex = cursor.getColumnIndexOrThrow(COLUMN_NAME);
                int ageIndex = cursor.getColumnIndexOrThrow(COLUMN_AGE);
                int classIndex = cursor.getColumnIndexOrThrow(COLUMN_CLASS);
                int sabaqIndex = cursor.getColumnIndexOrThrow(COLUMN_SABAQ);
                int sabaqiIndex = cursor.getColumnIndexOrThrow(COLUMN_SABAQI);
                int currentManzilIndex = cursor.getColumnIndexOrThrow(COLUMN_CURRENT_MANZIL);

                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                int age = cursor.getInt(ageIndex);
                String studentClass = cursor.getString(classIndex);
                String sabaq = cursor.getString(sabaqIndex);
                String sabaqi = cursor.getString(sabaqiIndex);
                int currentManzil = cursor.getInt(currentManzilIndex);

                Student student = new Student(id, name, age, studentClass, sabaq, sabaqi, currentManzil);
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return studentList;
    }

    public boolean isStudentExists(int rollNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_STUDENTS + " WHERE " + COLUMN_ID + " = " + rollNo;
        Cursor cursor = db.rawQuery(query, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public int addRecord(int rollNo, String sabaq, String sabaqi, int manzil) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SABAQ, sabaq);
        values.put(COLUMN_SABAQI, sabaqi);
        values.put(COLUMN_CURRENT_MANZIL, manzil);
        return db.update(TABLE_STUDENTS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(rollNo)});
    }

    public Student getStudent(int rollNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_STUDENTS + " WHERE " + COLUMN_ID + " = " + rollNo;
        Cursor cursor = db.rawQuery(query, null);
        Student student = null;
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
            int nameIndex = cursor.getColumnIndexOrThrow(COLUMN_NAME);
            int ageIndex = cursor.getColumnIndexOrThrow(COLUMN_AGE);
            int classIndex = cursor.getColumnIndexOrThrow(COLUMN_CLASS);
            int sabaqIndex = cursor.getColumnIndexOrThrow(COLUMN_SABAQ);
            int sabaqiIndex = cursor.getColumnIndexOrThrow(COLUMN_SABAQI);
            int currentManzilIndex = cursor.getColumnIndexOrThrow(COLUMN_CURRENT_MANZIL);

            int id = cursor.getInt(idIndex);
            String name = cursor.getString(nameIndex);
            int age = cursor.getInt(ageIndex);
            String studentClass = cursor.getString(classIndex);
            String sabaq = cursor.getString(sabaqIndex);
            String sabaqi = cursor.getString(sabaqiIndex);
            int currentManzil = cursor.getInt(currentManzilIndex);

            student = new Student(id, name, age, studentClass, sabaq, sabaqi, currentManzil);
        }
        cursor.close();
        db.close();
        return student;
    }
}
