package glub.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task for user to be reminded of.
 */
public class Task {
    /** Name of task to be completed. */
    private String task;
    /** Status of task. */
    private boolean isDone;
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    /**
     * Converts LocalDateTime object into dd MMM yyyy HH:mm format for display.
     * @param dateTime LocalDateTime object.
     * @return Date in dd MMM yyyy HH:mm format.
     */
    public String getDisplayDateTime(LocalDateTime dateTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return dateTime.format(format);
    }

    /**
     * Converts LocalDateTime object into dd-MM-yyyy HHmm format for saving.
     * @param dateTime LocalDateTime object.
     * @return Date in dd-MM-yyyy HHmm format.
     */
    public String getSaveDateTime(LocalDateTime dateTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return dateTime.format(format);
    }

    /**
     * Gets the icon corresponding to the status of the task.
     * @return Marked or unmarked icon/
     */
    public String getDoneIcon() {
        return isDone ? "X" : " ";
    }
    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Checks if task contains the specified search string.
     * @param searchString String to be checked against.
     * @return Boolean whether task contains search string.
     */
    public boolean checkMatch(String searchString) {
        return this.task.toLowerCase().contains(searchString);
    }

    /**
     * Gets the task in a string to be saved.
     * @return String to be saved.
     */
    public String toSaveFormat() {
        return String.format("%s|%s", getDoneIcon(), task);
    }

    /**
     * Gets the task string to be displayed.
     * @return Task string to be displayed.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", getDoneIcon(), task);
    }
}
