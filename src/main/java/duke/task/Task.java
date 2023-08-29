package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task in the chatbot.
 */
public class Task {
    /** Description of the task. */
    protected String description;
    /** Boolean that represents whether the task has been completed. */
    protected boolean isDone;

    /**
     * Constructor for a task.
     * 
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of whether the task is completed.
     * 
     * @return X if completed, empty if not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Changes the status of the task.
     */
    public void changeStatus() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns the DateTime of a task from parsing a String representation.
     * 
     * @param str The date and time to be converted to LocalDateTime.
     * @return The date and time in LocalDateTime type.
     */
    public LocalDateTime convertToDateTime(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(str, formatter);
    }

    /**
     * Returns the date and time of a task in a saveable format.
     * 
     * @param time Date and time to be converted.
     * @return String representation of the date and time.
     */
    public String saveTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return time.format(formatter);
    }

    /**
     * Returns the date and time of a task to be outputted.
     * 
     * @param time Date and time of the task.
     * @return Date and time of a task in the display format.
     */
    public String displayTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return time.format(formatter);
    }

    /**
     * Returns the output to be printed after executing commands.
     * 
     * @return Details of the task.
     */
    public String getOutputString() {
        return String.format("X | %d | %s", isDone ? 1 : 0, description);
    }

    /**
     * Returns the description of the task.
     * 
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description; 
    }
}
