package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline and provides methods to manage it.
 */
public class Deadline extends Task {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected LocalDateTime by;

    /**
     * Initializes a new Deadline task with the given description and deadline date.
     *
     * @param description The description of the task.
     * @param by          The deadline date in the format "yyyy-MM-dd HH:mm".
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Date given is in an invalid format.");
        }
    }

    /**
     * Returns a formatted string representation of the Deadline task, including its type, description, and deadline.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mm a")) + ")";
    }

    /**
     * Returns a string representation of the Deadline task to be saved into a file.
     *
     * @return A string representing the Deadline task in a file format.
     */
    @Override
    public String getDescription() {
        return "D" + super.getDescription() + " | " + this.by.format(formatter);
    }
}
