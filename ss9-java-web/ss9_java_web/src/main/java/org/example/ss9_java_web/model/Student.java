package org.example.ss9_java_web.model;

public class Student {
    private String name;
    private String email;
    private String className;
    private double point;

    public Student(String name, String email, String className, double point) {
        this.name = name;
        this.email = email;
        this.className = className;
        this.point = point;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

