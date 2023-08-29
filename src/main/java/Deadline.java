import java.time.LocalDateTime;

public class Deadline extends Task {
	public Deadline(String description, LocalDateTime timeline) {
		super(description);
		this.letter = "D";
		this.timeline = timeline;
	}

	@Override
	public String toString() {
		return super.toString() + " (by: " + super.formatLocalDateTimeToString(this.timeline) + ")";
	}

	@Override
	public String toFile() {
		return super.toFile() + " | " + this.timeline;
	}
}
