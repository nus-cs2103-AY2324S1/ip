import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
		super(description);
		this.startTime = startTime;
		this.endTime = endTime;
	}

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

	@Override
	public String toString() {
		String[] start = this.startTime.toString().split("T");
		String startDate = start[1] + " " + start[0];
		String[] end = this.endTime.toString().split("T");
		String endDate = end[1] + " " + end[0];
		return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
	}

	public String getIcon() {
		return "[E]" + super.getTask();
	}
}
