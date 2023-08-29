package duke;

import java.time.LocalDateTime;

public class Event extends Task {

	/**
	 * The start time of the event
	 */
	protected LocalDateTime from;
	/**
	 * The end time of the event
	 */
	protected LocalDateTime to;

	/**
	 * Constructor for Event
	 *
	 * @param description the description of the event
	 * @param from 			the start time of the event
	 * @param to 			the end time of the event
	 */
	public Event(String description, LocalDateTime from, LocalDateTime to) {
		super(description);
		this.letter = "E";
		this.from = from;
		this.to = to;
	}

	/**
	 * Returns a string representation of the event task.
	 *
	 * @return String representation of the event task.
	 */
	@Override
	public String toString() {
		return super.toString() + " (from: " + super.formatLocalDateTimeToString(this.from) + " to: " + formatLocalDateTimeToString(this.to) + ")";
	}

	/**
	 * Returns a string representation of the event task to be saved in the file.
	 *
	 * @return String representation of the event task to be saved in the file.
	 */
	@Override
	public String toFile() {
		return super.toFile() + " | " + this.from.toString() + " | " + this.to.toString();
	}
}
