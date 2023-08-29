package duke;

import java.time.LocalDateTime;

public class Deadline extends Task {
	/**
	 * Constructor for Deadline
	 *
	 * @param description the description of the deadline
	 * @param timeline    the time of the deadline
	 */
	public Deadline(String description, LocalDateTime timeline) {
		super(description);
		this.letter = "D";
		this.timeline = timeline;
	}

	/**
	 * Returns a string representation of the deadline task.
	 *
	 * @return String representation of the deadline task.
	 */
	@Override
	public String toString() {
		return super.toString() + " (by: " + super.formatLocalDateTimeToString(this.timeline) + ")";
	}

	/**
	 * Returns a string representation of the deadline task to be saved in the file.
	 *
	 * @return String representation of the deadline task to be saved in the file.
	 */
	@Override
	public String toFile() {
		return super.toFile() + " | " + this.timeline;
	}
}
