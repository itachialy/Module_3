package org.example.case_study.repository.studentRepo;
import org.example.case_study.model.Account;
import org.example.case_study.model.Class;
import org.example.case_study.model.Student;
import org.example.case_study.repository.BaseRepository;
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
    private static final String FIND_ALL = "CALL GetAllStudents()";
    private static final String FIND_ALL_CLASS = "select * from class;";
    private static final String INSERT_STUDENT = "CALL InsertStudent(?,?,?,?,?,?)";
    private static final String DELETE_STUDENT = "delete from student where student_id = ?";
    private static final String GETID_STUDENT = "CALL GetStudentById(?)";
    private static final String UPDATE_STUDENT = "CALL UpdateStudent(?, ?, ?, ?, ?, ?, ?)";
    private static final String SEARCH_BY_NAME = "CALL SearchStudentByName(?)";
    private static final String CHECK_LOGIN = "select * from admin_account where user_admin = ? and pass_admin = ?";

    @Override
    public List<Student> findAll() {
        Connection connection = baseRepository.getConnection();
        List<Student> studentList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("student_id");
                String name = resultSet.getString("student_name");
                Date birthday = resultSet.getDate("student_birthday");
                int gender = resultSet.getInt("student_gender");
                String email = resultSet.getString("student_email");
                double point = resultSet.getDouble("student_point");
                int classId = resultSet.getInt("class_id");
                String className = resultSet.getString("class_name");
                Class clazz = new Class(classId, className);
                Student student = new Student(id, name, birthday, gender, email, point, clazz);
                studentList.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }

    @Override
    public List<Class> findAllClass() {
        Connection connection = baseRepository.getConnection();
        List<Class> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CLASS);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()) {
                int classId = result.getInt("class_id");
                String className = result.getString("class_name");
                list.add(new Class(classId, className));
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
            ps.setDate(2, student.getBirthday());
            ps.setInt(3, student.getGender());
            ps.setString(4, student.getEmail());
            ps.setDouble(5, student.getPoint());
            ps.setInt(6, student.getClazz().getClassId());
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
            PreparedStatement ps = connection.prepareStatement(GETID_STUDENT);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            List<Student> list = toList(resultSet);
            if(!list.isEmpty()) {
                students = list.get(0);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return students;
    }
    private List<Student> toList(ResultSet resultSet) throws SQLException {
        List<Student> list = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("student_id");
            String name = resultSet.getString("student_name");
            Date birthday = resultSet.getDate("student_birthday");
            int gender = resultSet.getInt("student_gender");
            String email = resultSet.getString("student_email");
            double point = resultSet.getDouble("student_point");
            int classId = resultSet.getInt("class_id");
            String className = resultSet.getString("class_name");
            Class clazz = new Class(classId, className);
            list.add(new Student(id, name, birthday, gender, email, point, clazz));

        }
        return list;

    }
    @Override
    public void save(Student student) {
        Connection connection = baseRepository.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_STUDENT);
            ps.setString(1, student.getName());
            ps.setDate(2, new Date(student.getBirthday().getTime()));
            ps.setInt(3, student.getGender());
            ps.setString(4, student.getEmail());
            ps.setDouble(5, student.getPoint());
            ps.setInt(6, student.getClazz().getClassId());
            ps.setInt(7, student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public boolean checkEmailForCreate(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM student WHERE student_email = ?";
        Connection connection = baseRepository.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }
public boolean checkEmailForUpdate(int id, String email) throws SQLException {
    String sql = "SELECT COUNT(*) FROM student WHERE student_email = ? AND student_id != ?";
    Connection connection = baseRepository.getConnection();
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, email);
        statement.setInt(2, id);  // Bỏ qua học viên với ID hiện tại
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;  // Trả về true nếu có email trùng lặp
            }
        }
    }
    return false;
}

    @Override
    public List<Student> searchByName(String name) {
        Connection connection = baseRepository.getConnection();
        List<Student> studentList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_NAME);
            preparedStatement.setString(1, "%" + name + "%");  // Sử dụng LIKE để tìm kiếm tên có chứa chuỗi ký tự
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("student_id");
                String studentName = resultSet.getString("student_name");
                Date birthday = resultSet.getDate("student_birthday");
                int gender = resultSet.getInt("student_gender");
                String email = resultSet.getString("student_email");
                double point = resultSet.getDouble("student_point");
                int classId = resultSet.getInt("class_id");
                String className = resultSet.getString("class_name");
                Class clazz = new Class(classId, className);
                Student student = new Student(id, studentName, birthday, gender, email, point, clazz);
                studentList.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }

    @Override
    public Account checkLogin(String user, String pass) {
        Connection connection = baseRepository.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOGIN);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Account(resultSet.getString(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

}
