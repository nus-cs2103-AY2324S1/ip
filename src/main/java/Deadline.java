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
    private Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    /**
     * Parses the command string to create a Deadline instance.
     *
     * @param input The command string.
     * @return A new Deadline instance.
     * @throws DukeException If the input format is invalid.
     */
    public static Deadline createFromCommandString(String input) throws DukeException {
        String[] parts = input.split("/by ", 2);
        if (parts.length < 2) {
            throw new DukeException("Missing '/by' or date for deadline.");
        }
        LocalDate byDate;
        try {
            byDate = LocalDate.parse(parts[1].trim());
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("Deadline /by date should be in yyyy-mm-dd format (e.g. 2023-08-25)");
        }
        return new Deadline(parts[0].trim(), byDate);
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
