package duke.tasks;

public abstract class Task {
	private boolean isDone;

	private String description;

	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}

	/**
	 * Constructor for Task
	 *
	 * @param description the description of the task
	 */
	public Task(String description, String status) {
		this.description = description;
		this.isDone = status.equals("1");
	}

	public String getStatusIcon() {
		return (isDone ? "X" : " "); // mark done task with X
	}

	/**
	 * Returns a string representation of the task.
	 *
	 * @return String representation of the task.
	 */
	@Override
	public String toString() {
		return "[" + this.getStatusIcon() + "] " + this.description;
	}

	/**
	 * Returns a string representation of the task to be saved in the file.
	 *
	 * @return String representation of the task to be saved in the file.
	 */
	public String toFile() {
		return " | " + (this.isDone ? 1 : 0) + " | " + this.description;
	}

	public boolean isCompleted() {
		return this.isDone;
	}

	public void unmark() {
		this.isDone = false;
	}

	public void mark() {
		this.isDone = true;
	}
}
