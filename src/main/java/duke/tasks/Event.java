package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

	/**
	 * The "from" date/time
	 */
	private final LocalDateTime from;

	/**
	 * The "to" date/time
	 */
	private final LocalDateTime to;

	/**
	 * Constructor for Event
	 *
	 * @param description the description of the event
	 * @param from        the start time of the event
	 * @param to          the end time of the event
	 */
	public Event(String description, LocalDateTime from, LocalDateTime to) {
		super(description);
		this.from = from;
		this.to = to;
	}

	/**
	 * Constructor for Event
	 *
	 * @param description the description of the event
	 * @param from        the start time of the event
	 * @param to          the end time of the event
	 * @param status      the status of the event
	 */
	public Event(String description, LocalDateTime from, LocalDateTime to, String status) {
		super(description, status);
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
		return "[E]" + super.toString()
						+ " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"))
						+ " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
	}

	/**
	 * Returns a string representation of the event task for file storage.
	 *
	 * @return String representation of the event task for file storage.
	 */
	@Override
	public String toFile() {
		return "E" + super.toFile() + " | "
						+ this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"))
						+ " - " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
	}
}
