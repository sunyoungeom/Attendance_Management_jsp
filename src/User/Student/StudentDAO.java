package User.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import User.UserDAO;
import jdbc.JDBCProgram;

public class StudentDAO {
	private static DataSource dataSource = JDBCProgram.getInstance();

	public static void createStudent(String id, String name, String gender, int grade, int classNumber) {
		int userId = UserDAO.getUserPK(id);
		if (userId > 0) {
			String sql = "INSERT INTO Student (User_ID, Name, Gender, Grade, Class) VALUES (?, ?, ?, ?, ?)";
			try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

				stmt.setInt(1, userId);
				stmt.setString(2, name);
				stmt.setString(3, gender);
				stmt.setInt(4, grade);
				stmt.setInt(5, classNumber);

				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>();
		String sql = "SELECT * FROM Student";
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {
			while (resultSet.next()) {
				int userId = resultSet.getInt("User_ID");
				String name = resultSet.getString("Name");
				String gender = resultSet.getString("Gender");
				int grade = resultSet.getInt("Grade");
				int classNumber = resultSet.getInt("Class");
				students.add(new Student(userId, name, gender, grade, classNumber));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	public static int getStudentIDById(String Id) {
		String sql = "SELECT Student_ID FROM Student WHERE User_ID = ?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, UserDAO.getUserPK(Id));

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					int teacherId = resultSet.getInt("Student_ID");
					System.out.println(teacherId);
					return teacherId;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}
	
	public static Student getStudentInfo(String Id) {
	    String sql = "SELECT * FROM Student WHERE User_ID = ?";
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setInt(1, UserDAO.getUserPK(Id));

	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                int studentId = resultSet.getInt("Student_ID");
	                String name = resultSet.getString("Name");
	                String gender = resultSet.getString("Gender");
	                int grade = resultSet.getInt("Grade");
	                int classNumber = resultSet.getInt("Class");
	                
	                return new Student(studentId, name, gender, grade, classNumber);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}


	public static void updateStudent(String id, String name, String gender, int grade, int classNumber) {
		String sql = "UPDATE Student SET Name = ?, Gender = ?, Grade = ?, Class = ? WHERE User_ID = ?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, name);
			statement.setString(2, gender);
			statement.setInt(3, grade);
			statement.setInt(4, classNumber);
			statement.setInt(5, UserDAO.getUserPK(id));

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteStudent(String id) {
		String sql = "DELETE FROM Student WHERE User_ID = ?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, UserDAO.getUserPK(id));

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
