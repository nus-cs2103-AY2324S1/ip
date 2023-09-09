package duke.task;

import java.time.LocalDateTime;
/**
 * Represents an Event Task.
 * Has a description, startTime and endTime.
 */
public class Event extends Task {
	private final LocalDateTime startTime;
	private final LocalDateTime endTime;

	/**
	 * Represents an Event Task.
	 * @param description Describes the Event.
	 * @param startTime Time Event starts.
	 * @param endTime Time Event ends.
	 */

	public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
		super(description);
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns a String to be written into Storage.
	 * @return Reformatted String suitable for Storage.
	 */
	@Override
	public String writeToFile() {
		StringBuilder b = new StringBuilder();
		b.append("E" + " | ");
		if (this.isDone) {
			b.append("1" + " | ");
		} else {
			b.append("0" + " | ");
		}
		b.append(this.description).append(" | ");
		b.append(this.startTime).append(" | ").append(this.endTime);
		return b.toString();
	}

	/**
	 * @return String representation of Event.
	 */
	@Override
	public String toString() {
		String[] start = this.startTime.toString().split("T");
		String startDate = start[1] + " " + start[0];
		String[] end = this.endTime.toString().split("T");
		String endDate = end[1] + " " + end[0];
		return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
	}

	/**
	 * @return Icon of Task
	 */

	public String getIcon() {
		return "[E]" + super.getTask();
	}
}
