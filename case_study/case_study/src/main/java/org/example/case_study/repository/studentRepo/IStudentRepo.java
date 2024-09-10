package org.example.case_study.repository.studentRepo;

import org.example.case_study.model.Account;
import org.example.case_study.model.Student;

import java.util.List;

public interface IStudentRepo {
    List<Student> findAll();
    void addNewStudent(Student student);
    void deleteStudent(int id);
    List<Student> getStudentById( int id);
    void save(Student student);

    Account checkLogin(String user, String pass);
}
