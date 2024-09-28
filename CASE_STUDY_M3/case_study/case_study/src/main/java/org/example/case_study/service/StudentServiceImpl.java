package org.example.case_study.service;

import org.example.case_study.model.Account;
import org.example.case_study.model.Class;
import org.example.case_study.model.Student;
import org.example.case_study.repository.studentRepo.IStudentRepo;
import org.example.case_study.repository.studentRepo.StudentRepoImpl;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements IStudentService{
    private final IStudentRepo iStudentRepo = new StudentRepoImpl();
//    @Override


    @Override
    public List<Student> findAll() {
        return iStudentRepo.findAll();
    }

    @Override
    public List<Class> findAllClass() {
        return iStudentRepo.findAllClass();
    }

    @Override
    public void addNewStudent(Student student) {
        iStudentRepo.addNewStudent(student);
    }

    @Override
    public void deleteStudent(int id) {
        iStudentRepo.deleteStudent(id);
    }

    @Override
    public Student getStudentById(int id) {
        return iStudentRepo.getStudentById(id);
    }

    @Override
    public void save(Student student) {
        iStudentRepo.save(student);
    }

    @Override
    public boolean existEmail(String email) throws SQLException {
        return iStudentRepo.existEmail(email);
    }

    @Override
    public Account checkLogin(String user, String pass) {
        return iStudentRepo.checkLogin(user, pass);
    }

    @Override
    public List<Student> filterStudents(String name,String email, String className) {
        return iStudentRepo.filterStudents(name, email, className);
    }

}
