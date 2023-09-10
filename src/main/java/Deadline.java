import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private final LocalDate by;

    /**
     * Constructs a Deadline with the specified name and due date.
     *
     * @param name The name of the deadline.
     * @param by   The due date of the deadline.
     */
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    public static Deadline fromFileFormat(String[] parts) throws DukeException {
        boolean isDone = "1".equals(parts[1].trim());
        String name = parts[2].trim();
        LocalDate byDate;
        try {
            byDate = LocalDate.parse(parts[3].trim());
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("Deadline /by date should be in yyyy-mm-dd format (e.g. 2023-08-25)");
        }
        Deadline deadline = new Deadline(name, byDate);
        if (isDone) {
            deadline.markAsDone();
        }
        return deadline;
    }

    @Override
    public String toFileFormat() {
        return "D|" + (this.isDone ? "1" : "0") + "|" + this.name + "|" + this.by + "|";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = this.by.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
