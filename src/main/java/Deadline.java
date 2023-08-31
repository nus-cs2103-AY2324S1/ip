public class Deadline extends Task {

	private String endTime;
	public Deadline(String description, String endTime) {
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
		b.append(this.endTime);
		return b.toString();
	}
	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + this.endTime + ")";
	}
	public String getIcon() {
		return "[D]" + super.getTask();
	}
}
