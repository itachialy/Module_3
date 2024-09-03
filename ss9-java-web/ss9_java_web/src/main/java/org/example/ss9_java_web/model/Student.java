package org.example.ss9_java_web.model;

public class Student {
    private int id;
    private String name;
    private String className;
    private int gender;
    private double point;

    public Student(int id, String name, String className, int gender, double point) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.gender = gender;
        this.point = point;
    }

    public Student(String name, String className, int gender, double point) {
        this.name = name;
        this.className = className;
        this.gender = gender;
        this.point = point;
    }
    public Student(String name, String className, int gender, double point, int id) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.gender = gender;
        this.point = point;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPoint() {
        return point;
    }
    public void setPoint(double point) {
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

