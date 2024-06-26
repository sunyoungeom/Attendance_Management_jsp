package User.Student;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private int userId;
    private String name;
    private String gender;
    private int grade;
    private int classNumber;
}