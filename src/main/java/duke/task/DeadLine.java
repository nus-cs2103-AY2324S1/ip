package duke.task;
import java.time.LocalDateTime;

public class DeadLine extends Task {

	private final LocalDateTime endTime;
	public DeadLine(String description, LocalDateTime endTime) {
		super(description);
		this.endTime = endTime;
	}

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
	@Override
	public String toString() {
		String[] time = this.endTime.toString().split("T");
		String timeDate = time[1] + " " + time[0];
		return "[D]" + super.toString() + " (by: " + timeDate + ")";
	}
	public String getIcon() {
		return "[D]" + super.getTask();
	}
}
