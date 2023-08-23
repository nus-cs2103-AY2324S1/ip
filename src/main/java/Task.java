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


	public void mark() {
		this.isDone = true;
	}

	public void unmark() {
		this.isDone = false;
	}

	public String getStatusIcon() {
		return (isDone ? "X" : " "); // mark done task with X
	}
}
