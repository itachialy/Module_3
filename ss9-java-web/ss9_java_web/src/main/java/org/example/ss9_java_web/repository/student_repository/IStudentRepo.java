package org.example.ss9_java_web.repository.student_repository;

import org.example.ss9_java_web.model.Student;

import java.util.List;

public interface IStudentRepo {
    List<Student> findAll();
}
