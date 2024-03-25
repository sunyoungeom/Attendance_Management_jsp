package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import User.Teacher.Teacher;
import jdbc.JDBCProgram;

public class UserDAO {

	private static DataSource dataSource = JDBCProgram.getInstance();

	public static void createUser(String id, String password, UserType type) {
		String sql = "INSERT INTO user (ID, Password,UserType) VALUES (?, ?, ?)";
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, id);
			stmt.setString(2, password);
			stmt.setString(3, type.toString());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<User> getAllUser() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM user";
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {
			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String password = resultSet.getString("password");
				String type = resultSet.getString("UserType");
				User user = new User(id, password, UserType.valueOf(type));
				users.add(user);
//				System.out.println("ID: " + id + ", password: " + password + ", Age: " + type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public static void getUserById(String Id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE ID = ?")) {

			statement.setString(1, Id);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					String id = resultSet.getString("id");
					String password = resultSet.getString("password");
					String type = resultSet.getString("UserType");
					System.out.println("ID: " + id + ", password: " + password + ", UserType: " + type);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getUserPK(String Id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT User_ID FROM user WHERE ID = ?")) {

			statement.setString(1, Id);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					int userId = resultSet.getInt("User_ID");

//					System.out.println("User_ID: " + userId);
					return userId;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void updateUser(String Id, String password, UserType type) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE user SET Password = ?, UserType = ? WHERE ID = ?")) {

			statement.setString(1, password);
			statement.setString(2, type.toString());
			statement.setString(3, Id);

			statement.executeUpdate();
			System.out.println("Record updated successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteUser(String Id) {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM user WHERE ID = ?")) {

			pstmt.setString(1, Id);

			pstmt.executeUpdate();
			System.out.println("Record deleted successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
