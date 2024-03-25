package User.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {
    private int userId;
	private String name;
	private int grade;
	private int classNumber;
}
