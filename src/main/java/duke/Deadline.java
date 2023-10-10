package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Class to handle deadline type of tasks.
 */
public class Deadline extends Task {
    private LocalDate byDate;

    /**
     * Initialises a deadline task.
     *
     * @param description task description.
     * @param by deadline date.
     * @param taskType type of task.
     */
    public Deadline(String description, String by, char taskType) {
        super(description, taskType);
        this.byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the string representation of the deadline task to be stored in a file.
     *
     * @return File string representation of the task.
     */
    @Override
    public String toFileString() {
        return super.toFileString() + " | " +
                byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " +
                byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
