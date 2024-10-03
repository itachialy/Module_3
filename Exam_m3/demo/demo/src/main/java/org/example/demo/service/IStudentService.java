package org.example.demo.service;

import org.example.demo.model.ClassName;
import org.example.demo.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getAllStudent();
    List<ClassName> getAllClass();
    void addNewStudent(Student student);

    void deleteStudent(int id);
    Student getStudentById(int id);
    void save(Student student);


}
