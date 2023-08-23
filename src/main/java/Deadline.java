public class Deadline extends Task {
	protected String from;
	protected String to;

	public Deadline(String description, String timeline) {
		super(description);
		this.letter = "D";
		this.timeline = timeline;
	}

	@Override
	public String toString() {
		return "[" + this.letter + "]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: " + this.timeline + ")";
	}
}
