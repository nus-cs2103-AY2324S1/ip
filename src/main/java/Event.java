public class Event extends Task {
	private String startTime;
	private String endTime;

	public Event(String description, String startTime, String endTime) {
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
		return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
	}

	public String getIcon() {
		return "[E]" + super.getTask();
	}
}
