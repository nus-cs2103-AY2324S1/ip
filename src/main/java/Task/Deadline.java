package Task;

public class Deadline extends Task {
    private final String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D][" + (getIsDone() ? "X" : " ") + "] " + getDescription() + " (by: " + dueDate + ")";
    }
}

