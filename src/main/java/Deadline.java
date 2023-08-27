public class Deadline extends Task {
	private String by;

	public Deadline(String description, String by) throws DukeException {
		super(description, 'D');
		this.by = by;

		if (by.isEmpty())
			throw new DukeException("The by of a deadline cannot be empty.");
	}

	@Override
	public String toString() {
		return String.format("%s (by: %s)", super.toString(), by);
	}
}
