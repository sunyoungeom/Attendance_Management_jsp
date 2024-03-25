package User.Teacher;

import java.util.List;

public class TeacherService {
	private TeacherDAO dao;

	public TeacherService() {
		this.dao = new TeacherDAO();
	}

	public void createTeacher(String id, String name, int grade, int classNumber) {
		dao.createTeacher(id, name, grade, classNumber);
	}

	public List<Teacher> getAllTeachers() {
		return dao.getAllTeachers();
	}
	
	public void getTeacherById(String id) {
		dao.getTeacherIDById(id);
	}
	
	public Teacher getTeacherInfo(String loginId) {
		return dao.getTeacherInfo(loginId);
	}

	public void updateTeacher(String id, String name, int grade, int classNumber) {
		dao.updateTeacher(id, name, grade, classNumber);
	}

	public void deleteTeacher(String id) {
		dao.deleteTeacher(id);
	}
}
