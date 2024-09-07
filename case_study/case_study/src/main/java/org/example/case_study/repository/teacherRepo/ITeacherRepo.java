package org.example.case_study.repository.teacherRepo;

import org.example.case_study.model.Teacher;

import java.util.List;

public interface ITeacherRepo {
    List<Teacher> findAll();
}
