package org.example.case_study.service.studentService;

import org.example.case_study.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    void addNewStudent(Student student);
    void deleteStudent(int id);
    List<Student> getStudentById( int id);
    void save(Student student);
}
