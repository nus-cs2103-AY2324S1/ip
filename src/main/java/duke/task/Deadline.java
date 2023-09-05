package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class inherits from the Task class and represents a Deadline task type
 * with a description, a deadline and completion status
 */

public class Deadline extends Task {
    protected LocalDateTime date;

    /**
     * Constructor to build a Deadline Task Object with the task description and date
     * @param description The description of the Deadline Task
     * @param date The deadline by which the Deadline Task is due
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }
    /**
     * Overloaded constructor to build a Deadline Task Object read from the tasks
     * saved in the txt file with the task description, completion status and date
     * @param description The description of the Deadline Task
     * @param isDone represents the completion status of the task
     * @param date The deadline by which the Deadline Task is due
     */
    public Deadline(String description, boolean isDone, LocalDateTime date) {
        super(description, isDone);
        this.date = date;
    }



    /**
     * Returns a formatted string representation of the Deadline Task, including
     * its type, completion status, description, and due date/time.
     *
     * @return A string representing the Deadline Task.
     */
    @Override
    public String taskString() {
        return "[D]" + super.taskString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
    /**
     * Returns a string representation of the Deadline Task that can be used for
     * saving the deadline tasks to a txt file, including its type, completion status,
     * description, and due date.
     *
     * @return A string suitable for saving the Deadline Task to a text file.
     */
    @Override
    public String saveTaskString() {
        String status = (this.isDone ? "1" : "0");
        return "D" + "|" + status + "|" + this.description
                + "|" + this.date;
    }
}