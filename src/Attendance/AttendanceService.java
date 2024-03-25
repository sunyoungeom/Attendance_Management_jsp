package Attendance;

import java.time.LocalDate;
import java.util.List;

public class AttendanceService {

	public void createAttendance(String Id) {
		AttendanceDAO.createAttendance(Id);
	}

	public List<Attendance> getAllAttendances(String Id) {
		return AttendanceDAO.getAllAttendances(Id);
	}

	public void updateAttendance(int attendanceId, int studentId, int classroomId, LocalDate date, Status status) {
		AttendanceDAO.updateAttendance(attendanceId, studentId, classroomId, date, status);
	}

	public void deleteAttendance(int attendanceId) {
		AttendanceDAO.deleteAttendance(attendanceId);
	}

	public List<Attendance> getAttendanceById(String Id) {
		return AttendanceDAO.getAttendanceById(Id);
	}
}
