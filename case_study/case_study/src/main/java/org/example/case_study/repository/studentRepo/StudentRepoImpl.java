package org.example.case_study.repository.studentRepo;

import org.example.case_study.model.Student;
import org.example.case_study.repository.BaseRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

public class StudentRepoImpl implements IStudentRepo{
    private final BaseRepository baseRepository;

    {
        try {
            baseRepository = new BaseRepository();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static final String FIND_ALL = "SELECT * FROM student";
    private static final String INSERT_STUDENT =
            "insert into student(student_name, student_birthday, student_gender,student_email, student_point)\n" +
                    "values(?,?,?,?,?)";
    private static final String DELETE_STUDENT =
            "delete from student\n" +
                    "where student_id = ?" ;
    private static final String GETID_STUDENT =
            "select * from student\n" +
                    "where student_id = ?;";
    private static final String UPDATE_STUDENT =
            "update student\n" +
                    "set student_name = ?,\n" +
                    "student_birthday = ?,\n" +
                    "student_gender = ?,\n" +
                    "student_email = ?,\n" +
                    "student_point = ?\n" +
                    "where student_id = ? ";


    @Override
    public List<Student> findAll() {
        Connection connection = baseRepository.getConnection();
        List<Student> studentList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()){
                int id = resultSet.getInt("student_id");
                String name = resultSet.getString("student_name");
                LocalDate birthday = resultSet.getDate("student_birthday").toLocalDate();
                int gender = resultSet.getInt("student_gender");
                String email = resultSet.getString("student_email");
                double point = resultSet.getDouble("student_point");
//                int classId = resultSet.getInt("class_id");
                Student student = new Student(id, name, birthday, gender, email, point);
                studentList.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }

    @Override
    public void addNewStudent(Student student) {
        Connection connection = baseRepository.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_STUDENT);
            ps.setString(1, student.getName());
            ps.setDate(2, java.sql.Date.valueOf(student.getBirthday()));
            ps.setInt(3, student.getGender());
            ps.setString(4, student.getEmail());
            ps.setDouble(5, student.getPoint());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteStudent(int id) {
        Connection connection = baseRepository.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_STUDENT);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Student> getStudentById(int id) {
        Connection connection = baseRepository.getConnection();
        List<Student> listId = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(GETID_STUDENT);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                int id1 = resultSet.getInt("student_id");
                String name = resultSet.getString("student_name");
                LocalDate birthday = resultSet.getDate("student_birthday").toLocalDate();
                int gender = resultSet.getInt("student_gender");
                String email = resultSet.getString("student_email");
                double point = resultSet.getDouble("student_point");
                listId.add(new Student(id1, name, birthday, gender, email, point));
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return listId;
    }

    @Override
    public void save(Student student) {
        Connection connection = baseRepository.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_STUDENT);
            ps.setString(1, student.getName());
            ps.setDate(2, java.sql.Date.valueOf(student.getBirthday()));
            ps.setInt(3, student.getGender());
            ps.setString(4, student.getEmail());
            ps.setDouble(5, student.getPoint());
            ps.setInt(6, student.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
    }
