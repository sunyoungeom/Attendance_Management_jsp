package Classroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import User.Teacher.TeacherDAO;
import jdbc.JDBCProgram;

public class ClassroomDAO {
	private static DataSource dataSource = JDBCProgram.getInstance();

	public static void createClassroom(int teacherId, int grade, int classNumber) {
		String sql = "INSERT INTO Classroom (Teacher_ID, Grade, Class) VALUES (?, ?, ?)";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, teacherId);
			stmt.setInt(2, grade);
			stmt.setInt(3, classNumber);
			stmt.executeUpdate();

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int classroomId = generatedKeys.getInt(1);
					System.out.println(classroomId);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Classroom> getAllClassrooms() {
		List<Classroom> classrooms = new ArrayList<>();
		String sql = "SELECT * FROM Classroom";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet resultSet = stmt.executeQuery()) {
			while (resultSet.next()) {
				int classroomId = resultSet.getInt("Classroom_ID");
				int teacherId = resultSet.getInt("Teacher_ID");
				int grade = resultSet.getInt("Grade");
				int classNumber = resultSet.getInt("Class");
				Classroom classroom = new Classroom(teacherId, grade, classNumber);
				classrooms.add(classroom);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classrooms;
	}

	public static int getClassroomId(int grade, int classNumber) {
	    String sql = "SELECT Classroom_ID FROM Classroom WHERE Grade = ? AND Class = ?";
	    try (Connection conn = dataSource.getConnection(); 
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, grade);
	        stmt.setInt(2, classNumber);
	        try (ResultSet resultSet = stmt.executeQuery()) {
	            if (resultSet.next()) {
	                return resultSet.getInt("Classroom_ID");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}


	public static void updateClassroom(int classroomId, int teacherId, int grade, int classNumber) {
		String sql = "UPDATE Classroom SET Teacher_ID = ?, Grade = ?, Class = ? WHERE Classroom_ID = ?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, teacherId);
			stmt.setInt(2, grade);
			stmt.setInt(3, classNumber);
			stmt.setInt(4, classroomId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteClassroom(int classroomId) {
		String sql = "DELETE FROM Classroom WHERE Classroom_ID = ?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, classroomId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
