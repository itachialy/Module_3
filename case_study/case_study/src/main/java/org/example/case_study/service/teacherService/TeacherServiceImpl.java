package org.example.case_study.service.teacherService;

import org.example.case_study.model.Teacher;
import org.example.case_study.repository.teacherRepo.ITeacherRepo;
import org.example.case_study.repository.teacherRepo.TeacherRepoImpl;

import java.util.List;

public class TeacherServiceImpl implements ITeacherService{
    ITeacherRepo iTeacherRepo = new TeacherRepoImpl();
    @Override
    public List<Teacher> findAll() {
        return iTeacherRepo.findAll();
    }
}
