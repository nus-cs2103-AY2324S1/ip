package duke.tasks;

public class Todo extends Task {

	public Todo(String description) {
		super(description);
	}

	public Todo(String description, String status) {
		super(description, status);
	}

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}

	public String toFile() {
		return "T" + super.toFile();
	}

}
