public class Deadline extends Task {
	protected String by;

	/**
	 * Represents task that need to be done before a specific date/time
	 * @param description 	Description of the task
	 * @param by			Due date / time
	 */
	public Deadline(String description, String by) {
		super(description);
		this.by = by;
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + by + ")";
	}
}