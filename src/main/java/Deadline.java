public class Deadline extends Task {

    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline DeadlineCon(String description, String by) throws InvalidTaskCreationException {
        if (description.equalsIgnoreCase("")) {
            throw new InvalidTaskCreationException("OOPS!!! The description of a Deadline Task cannot be empty.");
        } else if (by.equalsIgnoreCase("")) {
            throw new InvalidTaskCreationException("OOPS!!! The deadline time of a Deadline Task cannot be empty.");
        } else {
            return new Deadline(description, by);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
