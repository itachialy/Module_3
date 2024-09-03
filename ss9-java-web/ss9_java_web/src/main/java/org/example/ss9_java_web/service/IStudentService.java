package org.example.ss9_java_web.service;

import org.example.ss9_java_web.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    void addNewStudent(Student student);
    void deleteStudent(int id);
    List<Student> getStudentById( int id);
    void save(Student student);


}