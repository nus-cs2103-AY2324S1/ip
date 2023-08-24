public class Deadlines extends Task {

	private String endTime;
	public Deadlines(String description, String endTime) {
		super(description);
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + this.endTime + ")";
	}
	public String getIcon() {
		return "[D]" + super.getTask();
	}
}
