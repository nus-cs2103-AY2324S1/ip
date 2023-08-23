public class Event extends Task {

	protected String from;
	protected String to;

	public Event(String description, String from, String to) {
		super(description);
		this.letter = "E";
		this.from = from;
		this.to = to;
	}

	@Override
	public String toString() {
		return "[" + this.letter + "]" + "[" + this.getStatusIcon() + "] " + this.description + " (from: " + this.from + " to: " + this.to + ")";
	}
}
