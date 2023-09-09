package tasks;


import java.time.LocalDateTime;

/**
 * The `Deadline` class represents a task with a specific deadline.
 * It extends the `Task` class and includes a deadline date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a `Deadline` object with the specified description and deadline.
     *
     * @param text The description of the deadline task.
     * @param by   The deadline date and time.
     */
    public Deadline(String text, LocalDateTime by) {
        super(text);
        this.by = by;
    }

    /**
     * Converts the `Deadline` object to a string in a save file format.
     *
     * @return A string representation of the `Deadline` object in save file format.
     */
    public String toSaveFormat() {
        return "D" + super.toSaveFormat() + " | " + this.by;
    }

    /**
     * Returns a string representation of the `Deadline` object for displaying to the user.
     *
     * @return A string representation of the `Deadline` object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(outputFormatter) + ")";
    }
}

