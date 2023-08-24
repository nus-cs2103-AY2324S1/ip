public class Events extends Task {
	private String startTime;
	private String endTime;

	public Events(String description, String startTime, String endTime) {
		super(description);
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
	}

	public String getIcon() {
		return "[E]" + super.getTask();
	}
}
