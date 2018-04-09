package com.example.princ.inclass08;

import java.io.Serializable;

public class Student implements Serializable{

    private String name,email,department,mood;
    private int checkedId;

    public Student(String name, String email, String department, String mood, int checkedId) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.mood = mood;
        this.checkedId=checkedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public int getCheckedId() {
        return checkedId;
    }

    public void setCheckedId(int checkedId) {
        this.checkedId = checkedId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", mood=" + mood +
                ", checkedId=" + checkedId +
                '}';
    }
}
