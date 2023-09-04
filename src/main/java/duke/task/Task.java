package duke.task;

/**
 * Represents a Task.
 * Task can be marked as done or unmarked.
 * Can be written to Storage.
 */
public class Task {
	protected String description;
	protected boolean isDone;

	/**
	 * Represents the Task.
	 * @param description Describes the Task.
	 */
	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}

	/**
	 * Marks Task as done.
	 */
	public void markAsDone() {
		this.isDone = true;
	}

	/**
	 * Unmarks the Task.
	 */

	public void unMark() {
		this.isDone = false;
	}

	/**
	 * @return String Representation of whether Task is done or not.
	 */

	public String getTask() {
		return isDone ? "[X]" : "[ ]";
	}

	/**
	 * @return String representation of Task to be written to text file.
	 */
	public String writeToFile() {
		return "";
	}

	/**
 	 * @return String representation of Task.
	 */
	@Override
	public String toString() {
		return isDone ? "[X]" + " " + this.description : "[ ]" + " " + this.description;
	}

}