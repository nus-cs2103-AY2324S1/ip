package ben;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task.
 */
public class Task {
    /**
     * Description of the task.
     */
    private final String description;

    /**
     * Whether the task has been completed.
     */
    private Boolean isCompleted;

    /**
     * Constructor that takes in a description and a boolean indicating whether the task has been completed.
     *
     * @param description Description of the task.
     * @param isCompleted Whether the task has been completed.
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Marks the task.
     */
    public void mark() {
        isCompleted = true;
    }

    /**
     * Unmarks the task.
     */
    public void unmark() {
        isCompleted = false;
    }

    /**
     * String representation of the task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        String marking;
        if (isCompleted) {
            marking = "[X]";
        } else {
            marking = "[ ]";
        }
        return marking + " " + description;
    }

    /**
     * String representation of task when it is saved to the file.
     *
     * @return String representation of task.
     */
    public String saveString() {
        return isCompleted.toString() + "|" + description;
    }

    /**
     * Formats dateTime to a string.
     *
     * @param dateTime The dateTime object.
     * @return String representation of dateTime object.
     */
    public String dateFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    /**
     * Formats dateTime to a string to be saved in the file.
     *
     * @param dateTime The dateTime object.
     * @return String representation of dateTime object.
     */
    public String saveDateFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }
}
