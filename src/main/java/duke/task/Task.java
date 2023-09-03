package duke.task;
public class Task {
	protected String description;
	protected boolean isDone;

	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}
	public void markAsDone() {
		this.isDone = true;
	}

	public void unMark() {
		this.isDone = false;
	}

	public String getTask() {
		return isDone ? "[X]" : "[ ]";
	}
	public String writeToFile() {
		return "";
	}
	@Override
	public String toString() {
		return isDone ? "[X]" + " " + this.description : "[ ]" + " " + this.description;
	}

}