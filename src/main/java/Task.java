public abstract class Task {
	protected String description;
	protected String letter;
	protected boolean isDone;
	protected String timeline;

	public Task(String description) {
		this.description = description;
		this.isDone = false;
		this.letter = "";
		this.timeline = "";
	}

	public String getLetter() {
		return this.letter;
	}

	public void mark() throws DukeException {
		if (this.isDone) {
			throw new DukeException("☹ OOPS!!! This task is already marked as done!");
		}
		System.out.println("Nice! I've marked this task as done:");
		this.isDone = true;
	}

	public void unmark() throws DukeException {
		if (!this.isDone) {
			throw new DukeException("☹ OOPS!!! This task is already marked as not done!");
		}
		System.out.println("OK, I've marked this task as not done yet:");
		this.isDone = false;
	}

	public String getStatusIcon() {
		return (isDone ? "X" : " "); // mark done task with X
	}

}
