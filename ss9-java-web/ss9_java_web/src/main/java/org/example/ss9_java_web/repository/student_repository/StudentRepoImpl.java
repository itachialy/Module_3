package org.example.ss9_java_web.repository.student_repository;

import org.example.ss9_java_web.model.Student;
import org.example.ss9_java_web.repository.student_repository.IStudentRepo;

import java.util.ArrayList;
import java.util.List;

public class StudentRepoImpl implements IStudentRepo {
    private static final List<Student> studentList = new ArrayList<>();
    static {
        studentList.add(new Student("Khoa", "khoa.huynh", "c0324m4",9));
        studentList.add(new Student("Thuong", "thuong.kim", "c0324m4",8));
        studentList.add(new Student("Viet", "viet.tam", "c0324m4",7));
    }

    @Override
    public List<Student> findAll() {
        return studentList;
    }
}
