package org.example.ss9_java_web.service;

import org.example.ss9_java_web.model.Student;
import org.example.ss9_java_web.repository.student_repository.IStudentRepo;
import org.example.ss9_java_web.repository.student_repository.StudentRepoImpl;

import java.util.List;

public class StudentServiceImpl implements IStudentService{
    private final IStudentRepo iStudentRepo = new StudentRepoImpl();

    @Override
    public List<Student> findAll() {
        return iStudentRepo.findAll();
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
    public List<Student> getStudentById(int id) {
        return iStudentRepo.getStudentById(id);
    }

    @Override
    public void save(Student student) {
        iStudentRepo.save(student);
    }
}
