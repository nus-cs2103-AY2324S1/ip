public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws DukeException {
        super(description, TaskType.DEADLINE);
        this.by = by;

        if (description.trim().isEmpty()) {
            throw new DukeException("The description of a deadline task cannot be empty.");
        }
        if (by.trim().isEmpty()) {
            throw new DukeException("The deadline cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}