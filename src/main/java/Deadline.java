public class Deadline extends Task {

    private String deadline;

    private Deadline(String taskName, String deadline) throws IncompleteDescriptionException {
        super(taskName);
        this.deadline = deadline;
    }

    public static Deadline create(String taskName, String deadline) throws IncompleteDescriptionException {
        if (deadline.isBlank()) throw new IncompleteDescriptionException();
        return new Deadline(taskName, deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
