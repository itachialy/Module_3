package org.example.case_study.repository.teacherRepo;

import org.example.case_study.model.Teacher;
import org.example.case_study.repository.BaseRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepoImpl implements ITeacherRepo{
    private final BaseRepository baseRepository;

    {
        try {
            baseRepository = new BaseRepository();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static final String FIND_ALL = "SELECT * FROM instructor";


    @Override
    public List<Teacher> findAll() {
        Connection connection = baseRepository.getConnection();
        List<Teacher> teacherList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()){
                int id = resultSet.getInt("instructor_id");
                String name = resultSet.getString("instructor_name");
                LocalDate birthday = resultSet.getDate("instructor_birthday").toLocalDate();
                int salary = resultSet.getInt("instructor_salary");
                Teacher teacher = new Teacher(id, name, birthday, salary);
                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teacherList;
    }
}
