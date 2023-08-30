public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (description.isEmpty() || by.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description / by of a deadline cannot be empty.");
        }
        this.by = by;
    }

    @Override
    public String toWrite() {
        return "D | " + super.toWrite() + " | " + by + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
