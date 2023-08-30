package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
	private LocalDateTime deadline;

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
	 * The string representation of the task.
	 *
	 * @return The string representation
	 **/
	@Override
	public String toString() {
		return "[D] " + super.toString()
						+ " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
	}

	public String toFile() {
		return "D" + super.toFile() + " | " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
	}
}
