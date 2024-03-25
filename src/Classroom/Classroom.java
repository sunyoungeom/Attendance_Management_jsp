package Classroom;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Classroom {
    private int teacherId;
    private int grade;
    private int classNumber;
}
