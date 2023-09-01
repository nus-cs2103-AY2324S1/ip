package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a deadline task in the Duke application.
 * It is a subclass of the Task class and inherits its properties and methods.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a new Deadline object with the specified description and deadline date and time.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline date and time of the task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Reads a deadline task from a file and returns a corresponding Deadline object.
     *
     * @param components An array of components parsed from a data file line.
     * @return A Deadline object representing the deadline task read from the file.
     */
    public static Deadline readFromFile(String[] components) {
        boolean isDone = components[1].equals("1");
        Deadline deadline = new Deadline(components[2], LocalDateTime.parse(components[3]));
        if(isDone) {
            deadline.markDone();
        }
        return deadline;
    }

    /**
     * Returns the task in the format suitable for writing to a data file.
     *
     * @return A string in the file format representing the deadline task.
     */
    @Override
    public String writeFileFormat() {
        return "D|" + super.writeFileFormat() + "|" + this.deadline;
    }

    /**
     * Returns a string representation of the deadline task, including its status icon, description,
     * and the deadline date and time.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D] "
                + super.toString()
                + "(by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + ")";
    }
}
