package duke.task;

public class ToDos extends Task {
	public ToDos(String description) {
		super(description);
	}

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
	@Override
	public String toString() {
		return "[T]" + super.toString();
	}

	public String getIcon() {
		return "[D]" + super.getTask();
	}
}
