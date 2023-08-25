package rat.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates a Deadline task.
 * A Deadline task is a task with a deadline as a String.
 * @author Keagan
 */
public class Deadline extends Task {

    /**
     * The deadline of the task, represented by a String.
     */
    private LocalDateTime deadline;

    private String deadlineString;

    /**
     * Constructor for a Deadline task.
     * @param deadline The deadline of the task.
     * @param name The name of the task.
     */
    protected Deadline(String deadline, String name) {
        super(name);
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.deadlineString = this.deadline.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm"));
    }

    protected Deadline(LocalDateTime deadline, String name) {
        super(name);
        this.deadline = deadline;
        this.deadlineString = this.deadline.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm"));
    }

    /**
     * Returns a String representation of a Deadline task.
     * The String representation of a Deadline task is its name prefixed by its status and type.
     * @return A String representation of a Deadline task.
     */
    @Override
    public String toString() {
        String taskType = "[D]";
        return taskType + super.toString() + " (by: " + this.deadlineString + ")";
    }

    @Override
    public String formatForFile() {
        String taskType = "D";
        return taskType + ", " + super.formatForFile() + ", " + this.deadline.toString();
    }

}
