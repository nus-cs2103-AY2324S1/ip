package duke.tasks;

public abstract class Task {
	/**
	 * The description of the task
	 */
	private final String description;
	/**
	 * The status of the task
	 */
	private boolean isDone;

	/**
	 * Constructor for Task
	 *
	 * @param description the description of the task
	 */
	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}

	/**
	 * Constructor for Task
	 *
	 * @param description the description of the task
	 * @param status      the status of the task
	 */
	public Task(String description, String status) {
		this.description = description;
		this.isDone = status.equals("1");
	}

	/**
	 * Returns the description of the task.
	 *
	 * @return String description of the task.
	 */
	public String getStatusIcon() {
		return (isDone ? "X" : " "); // mark done task with X
	}

	/**
	 * Returns the description of the task.
	 *
	 * @return String description of the task.
	 */
	@Override
	public String toString() {
		return "[" + this.getStatusIcon() + "] " + this.description;
	}

	/**
	 * Returns the description of the task for file storage.
	 *
	 * @return String description of the task for file storage.
	 */
	public String toFile() {
		return " | " + (this.isDone ? 1 : 0) + " | " + this.description;
	}

	/**
	 * Returns the description of the task.
	 *
	 * @return String description of the task.
	 */
	public boolean isCompleted() {
		return this.isDone;
	}

	/**
	 * Marks the task as done.
	 */
	public void unmark() {
		this.isDone = false;
	}

	/**
	 * Marks the task as done.
	 */
	public void mark() {
		this.isDone = true;
	}

	/**
	 * Returns the description of the task.
	 *
	 * @return String description of the task.
	 */
	public String getDescription() {
		return this.description;
	}
}
