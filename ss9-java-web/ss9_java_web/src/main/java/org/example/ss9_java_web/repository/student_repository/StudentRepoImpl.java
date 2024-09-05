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
            "insert into student(name, classname, gender, point)\n" +
                    "values(?,?,?,?)";
    private static final String DELETE_STUDENT =
            "delete from student\n" +
                    "where id = ?;" ;
    private static final String GETID_STUDENT =
            "select * from student\n" +
                    "where id = ?;";
    private static final String UPDATE_STUDENT =
            "update student\n" +
                    "set name = ?,\n" +
                    "classname = ?,\n" +
                    "gender = ?,\n" +
                    "point = ?\n" +
                    "where id = ? ";
    private static final String CALL_INSERT =
            "call insert_student(?,?,?,?)" ;


    @Override
    public List<Student> findAll() {
        Connection connection = baseRepository.getConnection();
        List<Student> list = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String className = resultSet.getString("classname");
                int gender = resultSet.getInt("gender");
                double point = resultSet.getDouble("point");
                list.add(new Student(id, name, className, gender, point));
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void addNewStudent(Student student) {
        Connection connection = baseRepository.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_STUDENT);
            ps.setString(1, student.getName());
            ps.setString(2, student.getClassName());
            ps.setInt(3, student.getGender());
            ps.setDouble(4, student.getPoint());
            ps.executeUpdate();

//            CallableStatement callableStatement = connection.prepareCall(CALL_INSERT);
//            callableStatement.setString(1, student.getName());
//            callableStatement.setString(2, student.getEmail());
//            callableStatement.setString(3, student.getClassName());
//            callableStatement.setDouble(4, student.getPoint());
//            callableStatement.executeUpdate();
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
                int studentId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String className = resultSet.getString("classname");
                int gender = resultSet.getInt("gender");
                double point = resultSet.getDouble("point");
                listId.add(new Student(studentId, name, className, gender, point));
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
            ps.setString(2, student.getClassName());
            ps.setInt(3, student.getGender());
            ps.setDouble(4, student.getPoint());
            ps.setInt(5, student.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
