package org.example.ss9_java_web.repository.student_repository;
import org.example.ss9_java_web.model.Student;
import org.example.ss9_java_web.repository.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepoImpl implements IStudentRepo {

    private final BaseRepository baseRepository;

    {
        try {
            baseRepository = new BaseRepository();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static final String FIND_ALL = "select * from student";
    private static final String INSERT_STUDENT =
            "insert into student(student_name,student_email,student_classname,student_point)" +
                    "values(?,?,?,?)";


    @Override
    public List<Student> findAll() {
        Connection connection = baseRepository.getConnection();
        List<Student> students = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()){
//                int id = resultSet.getInt("student_id");
                String name = resultSet.getString("student_name");
                String email = resultSet.getString("student_email");
                String className = resultSet.getString("student_classname");
                double point = resultSet.getDouble("student_point");
                students.add(new Student( name, email, className, point));
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return students;
    }

    @Override
    public void addNewStudent(Student student) {
        Connection connection = baseRepository.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getClassName());
            preparedStatement.setDouble(4, student.getPoint());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
