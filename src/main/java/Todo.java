public class Todo extends Task {

	/**
	 * Represents task without any date/time attached to it
	 * @param description Description of the task
	 */
	public Todo(String description) {
		super(description);
	}

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}