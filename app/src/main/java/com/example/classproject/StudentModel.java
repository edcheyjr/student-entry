package com.example.classproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StudentModel {
    int id;
    String firstname , lastname , regno  , course , gender;



    public StudentModel(@NonNull int id, @Nullable String firstname, @Nullable String lastname, @Nullable String regno, @Nullable String course, @Nullable String gender) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.regno = regno;
        this.course = course;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", regno='" + regno + '\'' +
                ", course='" + course + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
