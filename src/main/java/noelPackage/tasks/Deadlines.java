package noelPackage.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadlines extends Task {

    final String TASK_CHAR = "[D]";
    protected LocalDate dueDate;
    protected LocalTime dueTime;

    /**
     * Creates a new Deadline task with a given name, date, and time.
     *
     * @param taskName The name of the task.
     * @param deadlineDate The date of the deadline.
     * @param deadlineTime The time of the deadline.
     */
    public Deadlines(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(taskName);
        this.dueDate = deadlineDate;
        this.dueTime = deadlineTime;
    }

    /**
     * Returns a string representation of the dueDate
     *
     * @return dueDate of deadline task
     */
    public String formatDate() {
        return this.dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns a string representation of the dueTime
     *
     * @return dueTime of deadline task
     */
    public String formatTime() {
        return this.dueTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toFileString() {
        String taskDetails = super.getTaskName() + " | " + formatDate() + " " + formatTime();
        return "D | " + super.getStatusNumber() + " | " + taskDetails;
    }

    @Override
    public String toString() {
        return TASK_CHAR + super.toString() + " (by: " + formatDate() + " " + formatTime() + ")";
    }
}
