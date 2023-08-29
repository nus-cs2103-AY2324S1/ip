package duke;

import java.time.LocalDateTime;

public class Event extends Task {

	protected LocalDateTime from;
	protected LocalDateTime to;

	public Event(String description, LocalDateTime from, LocalDateTime to) {
		super(description);
		this.letter = "E";
		this.from = from;
		this.to = to;
	}

	@Override
	public String toString() {
		return super.toString() + " (from: " + super.formatLocalDateTimeToString(this.from) + " to: " + formatLocalDateTimeToString(this.to) + ")";
	}

	@Override
	public String toFile() {
		return super.toFile() + " | " + this.from.toString() + " | " + this.to.toString();
	}
}
