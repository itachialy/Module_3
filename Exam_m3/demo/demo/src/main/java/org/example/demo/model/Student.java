package org.example.demo.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Student {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private int id;
    private String name;
    private int gender;
    private String email;
    private ClassName clazz;

    public Student(int id, String name, int gender, String email, ClassName clazz) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.clazz = clazz;
    }

    public Student(String name,int gender, String email, ClassName clazz) {
        this.name = name;

        this.gender = gender;
        this.email = email;
        this.clazz = clazz;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClassName getClazz() {
        return clazz;
    }

    public void setClazz(ClassName clazz) {
        this.clazz = clazz;
    }
}
