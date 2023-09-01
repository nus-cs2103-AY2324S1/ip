package Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dueDate = LocalDate.parse(dueDate, formatter);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D][" + (getIsDone() ? "X" : " ") + "] " + getDescription() + " (by: " + dueDate.format(formatter) +
                ")";
    }
}

