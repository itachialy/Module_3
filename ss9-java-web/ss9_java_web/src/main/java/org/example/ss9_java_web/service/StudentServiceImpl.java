package org.example.ss9_java_web.service;

import org.example.ss9_java_web.model.Student;
import org.example.ss9_java_web.repository.student_repository.IStudentRepo;
import org.example.ss9_java_web.repository.student_repository.StudentRepoImpl;

import java.util.List;

public class StudentServiceImpl implements IStudentService{
    private IStudentRepo iStudentRepo = new StudentRepoImpl();
    @Override
    public List<Student> findAll() {
        return iStudentRepo.findAll();
    }
}
