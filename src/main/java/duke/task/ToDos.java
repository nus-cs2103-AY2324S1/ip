package duke.task;

/**
 * Represents a ToDos Task.
 * Only has a description of what is to be done.
 */
public class ToDos extends Task {
	public ToDos(String description) {
		super(description);
	}

	/**
	 * Returns a String to be written into Storage.
	 * @return Reformatted String suitable for Storage.
	 */
	@Override
	public String writeToFile() {
		StringBuilder b = new StringBuilder();
		b.append("T" + " | ");
		if (this.isDone) {
			b.append("1" + " | ");
		} else {
			b.append("0" + " | ");
		}
		b.append(this.description);
		return b.toString();
	}
	/**
	 * @return String representation of Task.
	 */
	@Override
	public String toString() {
		return "[T]" + super.toString();
	}

	public String getIcon() {
		return "[D]" + super.getTask();
	}
}
