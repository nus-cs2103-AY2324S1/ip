public class Deadline extends Task {
    private String due;

    public Deadline(String description) throws MissingDeadlineException {
        super(description.split(" /by ")[0]);
        try {
            this.due = description.split(" /by ")[1];
        } catch (Exception e) {
            throw new MissingDeadlineException();
        }

    }

    @Override
    public String toString() {
        String done = this.done ? "[X]" : "[ ]";
        return "[D]" + done + " " + this.name
                + " (by: " + this.due + ")";
    }
}
