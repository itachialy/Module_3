package org.example.demo.service;

import org.example.demo.common.BaseRepository;
import org.example.demo.model.ClassName;
import org.example.demo.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements IStudentService{
    private final BaseRepository baseRepository = new BaseRepository();
    public StudentServiceImpl() throws SQLException, ClassNotFoundException {
    }
    private static final String FIND_ALL = "select s.id, s.name, s.gender,\n" +
            "            s.email, c.class_id, c.class_name  from student s\n" +
            "            join class c on s.class_id = c.class_id\n" +
            "            ORDER BY s.id ASC;";
    private static final String GET_ALL_CLASS = "select * from class";
    private static final String DELETE_STUDENT = "delete from student where id = ?";
    private static final String GET_BY_ID = "select s.id, s.name, s.gender,\n" +
            "                    s.email, c.class_id, c.class_name  from student s\n" +
            "                    inner join class c on s.class_id = c.class_id\n" +
            "                    where id = ?";
    private static final String UPDATE_STUDENT = " update student\n" +
            "                    set name = ?,\n" +
            "                    gender = ?,\n" +
            "                    email = ?,\n" +
            "                    class_id = ? \n" +
            "                    where id = ?;";
    private static final String INSERT_STUDENT ="insert into student(name, gender,email, class_id)\n" +
            "values (?,?,?,?)";
    @Override
    public List<Student> getAllStudent() {
        Connection connection = baseRepository.getConnection();
        List<Student> list =new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int gender = resultSet.getInt("gender");
                String email = resultSet.getString("email");
                int classId = resultSet.getInt("class_id");
                String className = resultSet.getString("class_name");
                list.add(new Student(id, name, gender, email, new ClassName(classId,className)));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<ClassName> getAllClass() {
        Connection connection = baseRepository.getConnection();
        List<ClassName> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CLASS);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()) {
                int classId = result.getInt("class_id");
                String className = result.getString("class_name");
                list.add(new ClassName(classId, className));
            }
        } catch (SQLException e) {
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
            ps.setInt(2, student.getGender());
            ps.setString(3, student.getEmail());
            ps.setInt(4, student.getClazz().getClassId());
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
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Student getStudentById(int id) {
        Connection connection = baseRepository.getConnection();
        Student students = null;
        try {
            CallableStatement callableStatement = connection.prepareCall(GET_BY_ID);
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();

            List<Student> list = toList(resultSet);
            if(!list.isEmpty()) {
                students = list.get(0);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return students;
    }

    @Override
    public void save(Student student) {
        Connection connection = baseRepository.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_STUDENT);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getGender());
            ps.setString(3, student.getEmail());
            ps.setInt(4, student.getClazz().getClassId());
            ps.setInt(5, student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private List<Student> toList(ResultSet resultSet) throws SQLException {
        List<Student> list = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int gender = resultSet.getInt("gender");
            String email = resultSet.getString("email");
            int classId = resultSet.getInt("class_id");
            String className = resultSet.getString("class_name");
            list.add(new Student(id, name, gender, email, new ClassName(classId, className)));

        }
        return list;

    }
}
