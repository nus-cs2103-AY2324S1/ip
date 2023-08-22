public abstract class Task {
	protected String description;
	protected boolean isDone;

	/**
	 * Represents a task that has a completion status
	 * @param description Description of the task
	 */
	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}

	private String getStatusIcon() {
		// mark done task with X
		return (isDone ? "[X]" : "[ ]");
	}

	/**
	 * Marks the task as done.
	 */
	public void markAsDone() {
		this.isDone = true;
	}

	/**
	 * Marks the task as undone.
	 */
	public void markAsUndone() {
		this.isDone = false;
	}

	@Override
	public String toString() {
		return getStatusIcon() + " " + this.description;
	}
}