package User.Student;
import java.util.List;

public class StudentService {
    private StudentDAO dao;

    public StudentService() {
        this.dao = new StudentDAO();
    }

    public void createStudent(String id, String name, String gender, int grade, int classNumber) {
        dao.createStudent(id, name, gender, grade, classNumber);
    }
    
    public List<Student> getAllStudents() {
    	return dao.getAllStudents();
    }
    
    public Student getStudentInfo(String Id) {
    	return dao.getStudentInfo(Id);
    }

    public void updateStudent(String id, String name, String gender, int grade, int classNumber) {
        dao.updateStudent(id, name, gender, grade, classNumber);
    }

    public void deleteStudent(String id) {
        dao.deleteStudent(id);
    }
}
