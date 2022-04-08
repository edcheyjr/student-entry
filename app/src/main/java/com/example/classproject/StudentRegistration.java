package com.example.classproject;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.classproject.db.StudentEntryDBHelper;

public class StudentRegistration extends AppCompatActivity {

//    globally defined

    private EditText editFirstName ,editLastName, editREGNO, editCourse;
    private RadioGroup genderGroup;
    private RadioButton radioButtonSelected;
    private StudentEntryDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        final Button submitBtn = findViewById(R.id.ok_btn);
        final Button cancelBtn = findViewById(R.id.cancel_btn);

        editFirstName = findViewById(R.id.firstname);
        editLastName = findViewById(R.id.lastname);
        editREGNO = findViewById(R.id.regno);
        editCourse = findViewById(R.id.course);
        genderGroup = findViewById(R.id.gender);

//        initialize db using helper
        dbHelper = new StudentEntryDBHelper(getBaseContext(),null,null,1);

        submitBtn.setOnClickListener(view -> {
//            Get the selected gender from the radio button
            int selectedGender = genderGroup.getCheckedRadioButtonId();
            radioButtonSelected = (RadioButton)findViewById(selectedGender);
            feedDataToDB();
        });
        cancelBtn.setOnClickListener(view -> {
            Toast.makeText(this, "cancel submit going back...", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
        });
    }

    private void feedDataToDB(){
//        format text
        StudentModel student;
        try {
             student =new  StudentModel(
                     0
                     ,editFirstName.getText().toString()
                    ,editLastName.getText().toString()
                    ,editREGNO.getText().toString()
                    ,editCourse.getText().toString()
                    ,radioButtonSelected.getText().toString());
        }
        catch (Exception e) {
            Toast.makeText(this, "error fill all the inputs", Toast.LENGTH_SHORT).show();
            student =new  StudentModel(-1
                    ,null
                    ,null
                    ,null
                    ,null
                    ,null
            );
        }
        Boolean successValue = dbHelper.insertOneEntry(student);
       if(!successValue) {
           Toast.makeText(this, "failed to submit your details", Toast.LENGTH_SHORT).show();
       }else{
           Toast.makeText(this, "successfully registered", Toast.LENGTH_SHORT).show();
       }
    }


}