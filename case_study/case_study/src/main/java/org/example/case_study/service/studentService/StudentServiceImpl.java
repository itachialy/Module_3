package org.example.case_study.service.studentService;

import org.example.case_study.model.Student;
import org.example.case_study.repository.studentRepo.IStudentRepo;
import org.example.case_study.repository.studentRepo.StudentRepoImpl;

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
