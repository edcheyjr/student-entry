package com.example.classproject.db;

//import the queries from the StudentEntrySchema
import static com.example.classproject.db.StudentEntrySchema.SQL_CREATE_ENTRIES;
import static com.example.classproject.db.StudentEntrySchema.SQL_DELETE_ENTRIES;
import static com.example.classproject.db.StudentEntrySchema.SQL_SELECT_ALL;
import static com.example.classproject.db.StudentEntrySchema.StudentEntry;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.classproject.StudentModel;

import java.util.ArrayList;;
import java.util.List;



public class StudentEntryDBHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "StudentEntry.db";


    public StudentEntryDBHelper(@NonNull Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name = DATABASE_NAME, factory = null, version = DATABASE_VERSION);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }
    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public boolean insertOneEntry(@NonNull StudentModel student){
        //        Gets the data repository in write mode
        SQLiteDatabase database = this.getWritableDatabase();

//        Create a new map of values, where column names are the keys using ContentValues
        ContentValues values = new ContentValues();

//        map each column to each value
        values.put(StudentEntry.COLUMN_FIRST_NAME,student.getFirstname());
        values.put(StudentEntry.COLUMN_LAST_NAME,student.getLastname());
        values.put(StudentEntry.COLUMN_REG_NO,student.getRegno());
        values.put(StudentEntry.COLUMN_COURSE,student.getCourse());
        values.put(StudentEntry.COLUMN_GENDER,student.getGender());

//        Insert the new row, returning the primary key value of the new row
        long insertSuccess = database.insert(StudentEntrySchema.StudentEntry.TABLE_NAME, null, values);
        if(insertSuccess == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public  void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
    public List<StudentModel> getEveryone(){
        List<StudentModel> studentEntryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL_SELECT_ALL, null);
        if(cursor.moveToFirst()){
//            loop through the cursor (result set) and create new student object for each row
            do{
                int studentID = cursor.getInt( 0);
                String studentFirstName = cursor.getString(1);
                String studentLastName = cursor.getString(2);
                String studentRegNo= cursor.getString(3);
                String studentCourse = cursor.getString(4);
                String studentGender= cursor.getString(5);

                StudentModel studentModel = new StudentModel(studentID,studentFirstName,studentLastName,studentRegNo,studentCourse,studentGender);
                studentEntryList.add(studentModel);
            }while (cursor.moveToNext());
        }else {
//            failure : did fetch anything from the database
        }
//        close both db and cursor when done with it so that other people can use it;
        db.close();
        cursor.close();
        return studentEntryList;
    }
}
