package duke.exception;

/**
 * Duke IndexOutOfBoundsException for TaskList. Is thrown when trying to access
 * a task out of index
 */
public class TaskListOutOfBoundsException extends DukeException {
	/**
	 * Returns a TaskListOutOfBoundsException
	 *
	 * @param index the out of bounds index
	 * @return a TaskListOutOfBoundsException
	 */
	public TaskListOutOfBoundsException(int index) {
		super(String.format("%d is out of bounds for the tasks list.", index));
	}
}
