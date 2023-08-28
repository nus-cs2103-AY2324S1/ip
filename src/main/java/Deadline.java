public class Deadline extends Task {
    protected String dueDate;
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toLogString() {
        return String.format("D|%s|%s|%s", (isDone ? "X" : "O"), description, dueDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", dueDate);
    }
}

