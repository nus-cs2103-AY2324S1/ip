package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
	protected String description;
	protected String letter;
	protected boolean isDone;
	protected LocalDateTime timeline;

	public Task(String description) {
		this.description = description;
		this.isDone = false;
		this.letter = "";
	}

	public void mark() throws DukeException {
		if (this.isDone) {
			throw new DukeException("☹ OOPS!!! This task is already marked as done!");
		}
		this.isDone = true;
		System.out.println("Nice! I've marked this task as done:");
		System.out.println(this);
	}

	public void unmark() throws DukeException {
		if (!this.isDone) {
			throw new DukeException("☹ OOPS!!! This task is already marked as not done!");
		}
		this.isDone = false;
		System.out.println("OK, I've marked this task as not done yet:");
		System.out.println(this);
	}

	public String getStatusIcon() {
		return (isDone ? "X" : " "); // mark done task with X
	}

	@Override
	public String toString() {
		return "[" + this.letter + "]" + "[" + this.getStatusIcon() + "] " + this.description;
	}

	public String toFile() {
		return this.letter + " | " + (this.isDone ? 1 : 0) + " | " + this.description;
	}

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
