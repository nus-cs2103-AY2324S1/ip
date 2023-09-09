package duke.task;
import java.time.LocalDateTime;

/**
 * Represents a Deadline Task which.
 * Has a description and Deadline endTime.
 */
public class DeadLine extends Task {

	private final LocalDateTime endTime;

	/**
	 * Represents a DeadLine Task.
	 * @param description Describes the Task.
	 * @param endTime By what time must I complete this DeadLine Task.
	 */
	public DeadLine(String description, LocalDateTime endTime) {
		super(description);
		this.endTime = endTime;
	}

	/**
	 * Returns a String to be written into Storage.
	 * @return Reformatted String suitable for Storage.
	 */
	@Override
	public String writeToFile() {
		StringBuilder b = new StringBuilder();
		b.append("D" + " | ");
		if (this.isDone) {
			b.append("1" + " | ");
		} else {
			b.append("0" + " | ");
		}
		b.append(this.description).append(" | ");
		b.append(this.endTime.toString());
		return b.toString();
	}

	/**
	 * @return String representation of DeadLine.
	 */
	@Override
	public String toString() {
		String[] time = this.endTime.toString().split("T");
		String timeDate = time[1] + " " + time[0];
		return "[D]" + super.toString() + " (by: " + timeDate + ")";
	}

	/**
	 * @return Icon of DeadLine
	 */
	public String getIcon() {
		return "[D]" + super.getTask();
	}
}
