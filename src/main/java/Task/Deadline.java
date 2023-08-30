package Task;

public class Deadline extends Task {
    private final String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + dueDate;
    }

    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        Deadline deadline = new Deadline(parts[2], parts[3]);
        if (parts[1].equals("1")) {
            deadline.markDone();
        }
        return deadline;
    }

    @Override
    public String toString() {
        return "[D][" + (getIsDone() ? "X" : " ") + "] " + getDescription() + " (by: " + dueDate + ")";
    }
}

