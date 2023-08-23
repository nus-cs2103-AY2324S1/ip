public class Event extends Task {
	private String from;
	private String to;

	public Event(String description, String from, String to) {
		super(description, 'E');
		this.from = from;
		this.to = to;

		if (from.isEmpty())
			throw new DukeException("The from of a event cannot be empty.");
		if (to.isEmpty())
			throw new DukeException("The to of a event cannot be empty.");
	}

	@Override
	public String toString() {
		return String.format("%s (from: %s to: %s)", super.toString(), from, to);
	}
}
