public class Deadline extends Task {

    protected String date;

    public Deadline(String description, String date) throws DukeException {
        super(description, TaskType.DEADLINE);
        this.date = date;

        if (description.trim().isEmpty()) {
            throw new DukeException("The description of a deadline task cannot be empty.");
        }
        if (date.trim().isEmpty()) {
            throw new DukeException("The deadline cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}