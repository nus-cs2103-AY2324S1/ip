package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
	/**
	 * The time of the deadline
	 */
	private final LocalDateTime deadline;

	/**
	 * Constructor for Deadline
	 *
	 * @param description the description of the deadline
	 * @param deadline    the time of the deadline
	 */
	public Deadline(String description, LocalDateTime deadline) {
		super(description);
		this.deadline = deadline;
	}

	/**
	 * Constructor for Deadline
	 *
	 * @param description the description of the deadline
	 * @param deadline    the time of the deadline
	 * @param status      the status of the deadline
	 */
	public Deadline(String description, LocalDateTime deadline, String status) {
		super(description, status);
		this.deadline = deadline;
	}

	/**
	 * Returns the time of the deadline
	 *
	 * @return the time of the deadline
	 */
	@Override
	public String toString() {
		return "[D] " + super.toString()
						+ " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
	}

	/**
	 * Returns the time of the deadline
	 *
	 * @return the time of the deadline
	 */
	public String toFile() {
		return "D" + super.toFile() + " | " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
	}
}
