package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
	/**
	 * The description of the task
	 */
	protected String description;
	/**
	 * The letter representing the type of task
	 */
	protected String letter;
	/**
	 * The status of the task
	 */
	protected boolean isDone;
	/**
	 * The time of the task
	 */
	protected LocalDateTime timeline;

	/**
	 * Constructor for Task
	 *
	 * @param description the description of the task
	 */
	public Task(String description) {
		this.description = description;
		this.isDone = false;
		this.letter = "";
	}

	/**
	 * Marks the task as done
	 *
	 * @throws DukeException if the task is already marked as done
	 */
	public void mark() throws DukeException {
		if (this.isDone) {
			throw new DukeException("☹ OOPS!!! This task is already marked as done!");
		}
		this.isDone = true;
		System.out.println("Nice! I've marked this task as done:");
		System.out.println(this);
	}

	/**
	 * Marks the task as not done
	 *
	 * @throws DukeException if the task is already marked as not done
	 */
	public void unmark() throws DukeException {
		if (!this.isDone) {
			throw new DukeException("☹ OOPS!!! This task is already marked as not done!");
		}
		this.isDone = false;
		System.out.println("OK, I've marked this task as not done yet:");
		System.out.println(this);
	}

	/**
	 * Gets the status icon of the task
	 *
	 * @return String the status icon of the task
	 */
	public String getStatusIcon() {
		return (isDone ? "X" : " "); // mark done task with X
	}

	/**
	 * Returns a string representation of the task.
	 *
	 * @return String representation of the task.
	 */
	@Override
	public String toString() {
		return "[" + this.letter + "]" + "[" + this.getStatusIcon() + "] " + this.description;
	}

	/**
	 * Returns a string representation of the task to be saved in the file.
	 *
	 * @return String representation of the task to be saved in the file.
	 */
	public String toFile() {
		return this.letter + " | " + (this.isDone ? 1 : 0) + " | " + this.description;
	}

	/**
	 * Returns the description of the task
	 *
	 * @return String the description of the task
	 */
	public String formatLocalDateTimeToString(LocalDateTime dateTime) {
		String dayOfMonth = dateTime.getDayOfMonth() + getDayOfMonthSuffix(dateTime.getDayOfMonth());
		String month = dateTime.getMonth().toString();
		month = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();// Capitalize the month name
		String year = String.valueOf(dateTime.getYear());
		String hour = dateTime.format(DateTimeFormatter.ofPattern("h"));
		String minute = dateTime.format(DateTimeFormatter.ofPattern("mm"));
		String amPm = dateTime.format(DateTimeFormatter.ofPattern("a"));

		return String.format("%s of %s %s, %s:%s%s", dayOfMonth, month, year, hour, minute, amPm);
	}

	/**
	 * Returns the day of month suffix
	 *
	 * @param n the day of month
	 * @return String the day of month suffix
	 */
	public String getDayOfMonthSuffix(final int n) {
		if (n >= 11 && n <= 13) {
			return "th";
		}
		switch (n % 10) {
			case 1:
				return "st";
			case 2:
				return "nd";
			case 3:
				return "rd";
			default:
				return "th";
		}
	}

}
