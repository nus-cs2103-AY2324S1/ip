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

	public void mark() throws DukeException {
		if (this.isDone) {
			throw new DukeException("☹ OOPS!!! This task is already marked as done!");
		}
		this.isDone = true;
		System.out.println("Nice! I've marked this task as done:");
		System.out.println(this);
	}

	public void unmark() throws DukeException {
		if (!this.isDone) {
			throw new DukeException("☹ OOPS!!! This task is already marked as not done!");
		}
		this.isDone = false;
		System.out.println("OK, I've marked this task as not done yet:");
		System.out.println(this);
	}

	public String getStatusIcon() {
		return (isDone ? "X" : " "); // mark done task with X
	}

	@Override
	public String toString() {
		return "[" + this.letter + "]" + "[" + this.getStatusIcon() + "] " + this.description;
	}

	public String toFile() {
		return this.letter + " | " + (this.isDone ? 1 : 0) + " | " + this.description;
	}

}
