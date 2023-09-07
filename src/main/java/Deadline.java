import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent a task that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Creates a deadline task that is initially undone.
     *
     * @param description The description of the task that the user inputs
     * @param deadline The deadline that the user inputs
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Creates a deadline task that could be done or undone.
     *
     * @param isDone Whether the task is done or undone
     * @param description The description of the task that the user inputs
     * @param deadline The deadline that the user inputs
     */
    public Deadline(boolean isDone, String description, LocalDate deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }

    /**
     * Creates a deadline task from the save format.
     *
     * @param formattedTask The string representation of the deadline task
     * @return A deadline task
     */
    public static Deadline createFromSaveFormat(String formattedTask) {
        String[] args = formattedTask.split(" \\| ");
        boolean isDone = args[1].equals("1");
        LocalDate ld = LocalDate.parse(args[3], DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return new Deadline(isDone, args[2], ld);
    }

    @Override
    public String toSaveFormat() {
        return "D | " + super.toSaveFormat() + " | " +
                this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.description +
                " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
