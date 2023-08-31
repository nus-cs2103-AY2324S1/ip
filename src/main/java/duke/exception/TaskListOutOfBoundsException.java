package duke.exception;

public class TaskListOutOfBoundsException extends DukeException {
	public TaskListOutOfBoundsException(int index) {
		super(String.format("%d is out of bounds for the tasks list.", index));
	}
}
