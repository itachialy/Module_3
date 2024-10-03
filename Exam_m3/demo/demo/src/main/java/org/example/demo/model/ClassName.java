package org.example.demo.model;

public class ClassName {
    private int classId;
    private String className;

    public ClassName(int classId, String className) {
        this.classId = classId;
        this.className = className;
    }

    public ClassName(int classId) {
        this.classId = classId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
