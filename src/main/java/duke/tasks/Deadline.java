package duke.tasks;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * A subclass of Task. Contains a description, isDone and by.
 */
public class Deadline extends Task {
    /**
     * by contains the dateTime by which the Task is to be done.
     */
    protected LocalDateTime by;

    /**
     * Constructor for the Deadline class.
     *
     * @param description Name of the Task.
     * @param by Date and time to complete Task by.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for the Deadline class.
     *
     * @param description Name of the Task.
     * @param by Date and time to complete Task by.
     * @param isDone If task is completed.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the String representation of the Deadline, along with the
     * indication of whether it isDone and the by of the Deadline.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        String output = by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
        return "[D]" + super.toString() + " (by: " + output + ")";
    }

    /**
     * Returns the String to be written into the .txt file for saving
     * of the file.
     *
     * @return String to be written into the save file.
     */
    @Override
    public String write() {
        String output = by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        return "D | " + super.write() + " | " + output;
    }

    public LocalDateTime getBy() {
        return by;
    }
}