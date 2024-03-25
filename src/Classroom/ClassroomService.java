package Classroom;

import java.util.List;

public class ClassroomService {

    public void createClassroom(int teacherId, int grade, int classNumber) {
        ClassroomDAO.createClassroom(teacherId, grade, classNumber);
    }

    public List<Classroom> getAllClassrooms() {
        return ClassroomDAO.getAllClassrooms();
    }

    public int getClassroomIdById(int grade, int classNumber) {
        return ClassroomDAO.getClassroomId(grade, classNumber);
    }

    public void updateClassroom(int classroomId, int teacherId, int grade, int classNumber) {
        ClassroomDAO.updateClassroom(classroomId, teacherId, grade, classNumber);
    }

    public void deleteClassroom(int classroomId) {
        ClassroomDAO.deleteClassroom(classroomId);
    }
}
