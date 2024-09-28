package org.example.case_study.service;

import org.example.case_study.model.Account;
import org.example.case_study.model.Class;
import org.example.case_study.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    List<Class> findAllClass();
    void addNewStudent(Student student);
    void deleteStudent(int id);
    Student getStudentById( int id);
    void save(Student student);
    boolean existEmail(String email) throws SQLException;
    List<Student> filterStudents(String name,String email, String className );
    Account checkLogin(String user, String pass);


}
