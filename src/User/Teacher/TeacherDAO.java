package User.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import User.UserDAO;
import jdbc.JDBCProgram;

public class TeacherDAO {
	private static DataSource dataSource = JDBCProgram.getInstance();

	public static void createTeacher(String id, String name, int grade, int classNumber) {
		int userId = UserDAO.getUserPK(id);
		if (userId > 0) {
			String sql = "INSERT INTO Teacher (User_ID, Name, Grade, Class) VALUES (?, ?, ?, ?)";
			try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

				stmt.setInt(1, userId);
				stmt.setString(2, name);
				stmt.setInt(3, grade);
				stmt.setInt(4, classNumber);

				stmt.executeUpdate();
				System.out.println("Teacher created successfully.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Teacher> getAllTeachers() {
		List<Teacher> teachers = new ArrayList<>();
		String sql = "SELECT * FROM Teacher";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				int userId = resultSet.getInt("User_ID");
				String name = resultSet.getString("Name");
				int grade = resultSet.getInt("Grade");
				int classNumber = resultSet.getInt("Class");
				Teacher teacher = new Teacher(userId, name, grade, classNumber);
				teachers.add(teacher);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teachers;
	}

	public static int getTeacherIDById(String Id) {
		String sql = "SELECT Teacher_ID FROM Teacher WHERE User_ID = ?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, UserDAO.getUserPK(Id));

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					int teacherId = resultSet.getInt("Teacher_ID");
					System.out.println(teacherId);
					return teacherId;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static Teacher getTeacherInfo(String Id) {
		String sql = "SELECT * FROM Teacher WHERE User_ID = ?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, UserDAO.getUserPK(Id));

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					int userId = UserDAO.getUserPK(Id);
					String name = resultSet.getString("Name");
	                int grade = resultSet.getInt("Grade");
	                int classNumber = resultSet.getInt("Class");
	                return new Teacher(userId, name, grade, classNumber);				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void updateTeacher(String id, String name, int grade, int classNumber) {
		String sql = "UPDATE Teacher SET Name = ?, Grade = ?, Class = ? WHERE User_ID = ?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, name);
			statement.setInt(2, grade);
			statement.setInt(3, classNumber);
			statement.setInt(4, UserDAO.getUserPK(id));

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteTeacher(String id) {
		String sql = "DELETE FROM Teacher WHERE User_ID = ?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, UserDAO.getUserPK(id));

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
