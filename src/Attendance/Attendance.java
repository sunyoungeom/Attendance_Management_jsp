package Attendance;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Attendance {
	private int studnetId;
	private int classroomId;
	private LocalDate date;
	private Status status;
}
