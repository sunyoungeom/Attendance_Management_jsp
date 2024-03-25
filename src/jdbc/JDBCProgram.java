package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import User.UserType;

public class JDBCProgram {
	private static DataSource dataSource = setupDataSource();


	public static DataSource getInstance() {
		return dataSource;
	}

	private static DataSource setupDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/Attendance_Management");
		ds.setUsername("root");
		ds.setPassword("root1234");

		return ds;
	}

}

/*
 * 
 * private static void createRecord(String name, int age) { try (Connection
 * connection = dataSource.getConnection(); Statement statement =
 * connection.createStatement()) { String query =
 * String.format("INSERT INTO mytable (name, age) VALUES ('%s', %d)", name,
 * age); statement.executeUpdate(query);
 * System.out.println("Record created successfully."); } catch (SQLException e)
 * { e.printStackTrace(); } }
 */
