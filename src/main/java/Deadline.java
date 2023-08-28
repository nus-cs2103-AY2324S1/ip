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
		return super.toString() + " (by: " + this.timeline + ")";
	}

	@Override
	public String toFile() {
		return super.toFile() + " | " + this.timeline;
	}
}
