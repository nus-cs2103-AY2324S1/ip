import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
	protected LocalDateTime from;
	protected LocalDateTime to;

	/**
	 * Represents task that start at a specific date/time and ends at a specific date/time
	 * @param description 	Description of the task
	 * @param from			Start date / time
	 * @param to			End date / time
	 */
	public Event(String description, LocalDateTime from, LocalDateTime to) {
		super(description);
		this.from = from;
		this.to = to;
	}

	@Override
	public String serialize() {
		return String.format("E | %d | %s | %s | %s",
				super.isDone ? 1 : 0,
				super.description,
				this.from.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")),
				this.to.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
	}

	@Override
	public String toString() {
		return "[E]" + super.toString()
				+ " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm"))
				+ " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")";
	}
}