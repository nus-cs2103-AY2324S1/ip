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
     * The deadline of the task, represented by a LocalDateTime object.
     */
    private LocalDateTime deadline;

    /**
     * The deadline of the task, represented by a String.
     */
    private String deadlineString;

    /**
     * Constructor for a Deadline task.
     * This constructor uses a String to represent the deadline of the task.
     * Used during regular runtime of Rat where user enters data from the command line.
     * @param deadline The deadline of the task.
     * @param name The name of the task.
     */
    protected Deadline(String deadline, String name) {
        super(name);
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.deadlineString = this.deadline.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm"));
    }

    /**
     * Constructor for a Deadline task.
     * This constructor uses a LocalDateTime object to represent the deadline of the task.
     * Used when reading data from a file.
     * @param deadline The deadline of the task.
     * @param name The name of the task.
     */
    protected Deadline(LocalDateTime deadline, String name) {
        super(name);
        this.deadline = deadline;
        this.deadlineString = this.deadline.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm"));
    }

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
