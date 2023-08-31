package duke.tasks;

public class Todo extends Task {

	/**
	 * Constructor for Todo
	 *
	 * @param description the description of the todo
	 */
	public Todo(String description) {
		super(description);
	}

	/**
	 * Constructor for Todo
	 *
	 * @param description the description of the todo
	 * @param status      the status of the todo
	 */
	public Todo(String description, String status) {
		super(description, status);
	}

	/**
	 * Returns the description of the todo
	 *
	 * @return the description of the todo
	 */
	@Override
	public String toString() {
		return "[T]" + super.toString();
	}

	/**
	 * Returns the description of the todo for file storage
	 *
	 * @return the description of the todo for file storage
	 */
	public String toFile() {
		return "T" + super.toFile();
	}

}
