package User;

import java.util.List;
import java.util.Scanner;

import User.Student.StudentDAO;
import User.Teacher.TeacherDAO;

public class UserService {

	private UserDAO dao;
	private TeacherDAO tDao;
	private StudentDAO sDao;

	public UserService() {
		this.dao = new UserDAO();
	}

	public void createUser(String id, String password, UserType type) {
        dao.createUser(id, password, type);
        
        /* 콘솔 입력
        switch (type) {
            case TEACHER:
                Scanner scanner1 = new Scanner(System.in);
                System.out.print("이름: ");
                String name = scanner1.nextLine();
                System.out.print("학년: ");
                int grade = scanner1.nextInt();
                System.out.print("반: ");
                int classNumber = scanner1.nextInt();
                tDao.createTeacher(id, name, grade, classNumber);
                break;
            case STUDENT:
                Scanner scanner2 = new Scanner(System.in);
                System.out.print("이름: ");
                String studentName = scanner2.nextLine();
                System.out.print("성별[남/여]: ");
                String gender = scanner2.nextLine();
                System.out.print("학년: ");
                int studentGrade = scanner2.nextInt();
                System.out.print("반: ");
                int studentClass = scanner2.nextInt();
                sDao.createStudent(id, studentName, gender, studentGrade, studentClass);
                break;
            default:
                System.out.println("잘못된 유저 타입");
                break;
        }
        */
    }

	public List<User> getAllUsers() {
		return dao.getAllUser();
	}

	public void getUserById(String id) {
		dao.getUserById(id);
	}

	public void updateUser(String id, String password, UserType type) {
		dao.updateUser(id, password, type);
	}

	public void deleteUser(String id) {
		dao.deleteUser(id);
	}
}
