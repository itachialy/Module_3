package org.example.case_study.model;

import java.time.LocalDate;

public class Student {
    private int id;
    private String name;
    private LocalDate birthday;
    private int gender;
    private String email;
    private double point;
    private String account;
    private Class clazz;

    public Student(int id, String name, LocalDate birthday, int gender, String email, double point, String account, Class classId) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.point = point;
        this.email = email;
        this.account = account;
        this.clazz = classId;
    }

    public Student(String name, LocalDate birthday, int gender, String email, double point, String account, Class clazz) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.point = point;
        this.email = email;
        this.account = account;
        this.clazz = clazz;
    }

    public Student(String name, LocalDate birthday, int gender, String email, double point, Class clazz) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.point = point;
        this.email = email;
        this.clazz = clazz;
    }

    public Student(int id, String name, LocalDate birthday, int gender, String email, double point, Class clazz) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
        this.point = point;
        this.clazz = clazz;
    }

    public Student(int id, String name, LocalDate birthday, int gender, String email, double point) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
        this.point = point;
    }

    public Student(String name, LocalDate birthday, int gender, String email, double point) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
        this.point = point;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }


}
