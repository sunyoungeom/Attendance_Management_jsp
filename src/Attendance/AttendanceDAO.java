package Attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Classroom.ClassroomDAO;
import User.Student.Student;
import User.Student.StudentDAO;
import User.Teacher.Teacher;
import User.Teacher.TeacherDAO;
import jdbc.JDBCProgram;

public class AttendanceDAO {
    private static DataSource dataSource = JDBCProgram.getInstance();

    public static void createAttendance(String Id) {
        String sql = "INSERT INTO Attendance (Student_ID, Classroom_ID, Date, Status) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        	 Student student = StudentDAO.getStudentInfo(Id); // 학생 정보 가져오기
             if (student != null) {
                 int studentId = StudentDAO.getStudentIDById(Id); // 학생 ID 가져오기
                 int classroomId = ClassroomDAO.getClassroomId(student.getGrade(), student.getClassNumber()); // 클래스룸 ID 가져오기
                 java.sql.Date date = new java.sql.Date(System.currentTimeMillis()); // 현재 날짜 가져오기

                 stmt.setInt(1, studentId);
                 stmt.setInt(2, classroomId);
                 stmt.setDate(3, date);
                 stmt.setString(4, Status.PRESENT.toString());
                 stmt.executeUpdate();
             } else {
                 System.out.println("해당하는 학생 정보가 없습니다.");
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Attendance> getAllAttendances(String Id) {
        List<Attendance> attendances = new ArrayList<>();
        String sql = "SELECT * FROM Attendance WHERE Classroom_ID = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
        	Teacher t = TeacherDAO.getTeacherInfo(Id);
               stmt.setInt(1, ClassroomDAO.getClassroomId(t.getGrade(), t.getClassNumber()));
               try (ResultSet resultSet = stmt.executeQuery()) {
                   while (resultSet.next()) {
                       int studentId = resultSet.getInt("Student_ID");
                       int classroomId = resultSet.getInt("Classroom_ID");
                       LocalDate date = resultSet.getDate("Date").toLocalDate();
                       Status status = Status.valueOf(resultSet.getString("Status"));
                       Attendance attendance = new Attendance(studentId, classroomId, date, status);
                       attendances.add(attendance);
                   }
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
           return attendances;
    }
    
    public static List<Attendance> getAttendanceById(String Id) {
    	 List<Attendance> attendances = new ArrayList<>();
        String sql = "SELECT * FROM Attendance WHERE Student_ID = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	int studentId = StudentDAO.getStudentIDById(Id);
            stmt.setInt(1, studentId);
            try (ResultSet resultSet = stmt.executeQuery()) {
            	while (resultSet.next()) {
                    int classroomId = resultSet.getInt("Classroom_ID");
                    LocalDate date = resultSet.getDate("Date").toLocalDate();
                    Status status = Status.valueOf(resultSet.getString("Status"));
                    Attendance attendance = new Attendance(studentId, classroomId, date, status);
                    attendances.add(attendance);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }

    public static void updateAttendance(int attendanceId, int studentId, int classroomId, LocalDate date, Status status) {
        String sql = "UPDATE Attendance SET Student_ID = ?, Classroom_ID = ?, Date = ?, Status = ? WHERE Attendance_ID = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, classroomId);
            stmt.setDate(3, java.sql.Date.valueOf(date));
            stmt.setString(4, status.toString());
            stmt.setInt(5, attendanceId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAttendance(int attendanceId) {
        String sql = "DELETE FROM Attendance WHERE Attendance_ID = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, attendanceId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
