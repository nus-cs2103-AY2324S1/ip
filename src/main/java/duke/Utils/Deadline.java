package duke.utils;

import java.time.LocalDateTime;

/**
 * The Deadline class represents a task with a deadline in the Duke application.
 * It extends the Task class and includes additional information about the deadline.
 */
public class Deadline extends Task {

    private LocalDateTime start;

    /**
     * Constructs a new Deadline object with the specified title and start date and time.
     *
     * @param title The title of the deadline task.
     * @param start The date and time of the deadline.
     */
    protected Deadline(String title, LocalDateTime start) {
        super(title, Task.Type.DEADLINE);
        this.start = start;
    }

    /**
     * Constructs a new Deadline object with the specified title, marked status, and start date and time.
     *
     * @param title  The title of the deadline task.
     * @param marked A boolean indicating whether the task is marked as completed.
     * @param start  The date and time of the deadline.
     */
    protected Deadline(String title, boolean marked, LocalDateTime start) {
        this(title, start);
        if (marked) {
            this.mark();
        }
    }

    /**
     * Creates a new Deadline object from an array of arguments.
     *
     * @param args An array of strings containing information to create a Deadline object.
     * @return A new Deadline object created from the provided arguments.
     */
    protected static Deadline of(String[] args) {
        boolean marked = FileIO.assertBoolean(args[1]);
        String title = FileIO.assertString(args[2]);
        LocalDateTime start = FileIO.assertDateTime(args[3]);
        return new Deadline(title, marked, start);
    }

    /**
     * Converts the Deadline object to a CSV (Comma-Separated Values) string.
     *
     * @return A CSV string representation of the Deadline object.
     */
    @Override
    public String toCsv() {
        return FileIO.joinCsv(
            this.type(),
            this.marked(),
            this.name(),
            Task.dateToString(start)
        );
    }

    /**
     * Returns a string representation of the Deadline object, including its type and deadline information.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return this.type() + super.toString() + " (by: " + Task.dateToString(start) + ")";
    }
}
