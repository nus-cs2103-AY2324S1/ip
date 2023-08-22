public class Event extends Task {
	protected String from;
	protected String to;

	/**
	 * Represents task that start at a specific date/time and ends at a specific date/time
	 * @param description 	Description of the task
	 * @param from			Start date / time
	 * @param to			End date / time
	 */
	public Event(String description, String from, String to) {
		super(description);
		this.from = from;
		this.to = to;
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
	}
}