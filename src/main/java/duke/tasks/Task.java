package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task entered by a user.
 */
public abstract class Task {
    public final String type;
    private final String description;
    private boolean isMarked;

    /**
     * Public constructor to be called by child classes.
     *
     * @param description the description describing the task
     * @param type the type of task
     * @param isMarked boolean value tracking whether the task has been marked
     */
    public Task(String description, String type, boolean isMarked) {
        this.description = description;
        this.type = type;
        this.isMarked = isMarked;
    }

    /**
     * An abstract method to reconstruct the original message passed by the user.
     *
     * @return text string of the original user entered message
     */
    public abstract String getOriginalMessage();

    /**
     * Getter method for the description field.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter method for the isMarked field.
     *
     * @return the boolean value if the task is marked
     */
    public boolean getMarked() {
        return this.isMarked;
    }

    /**
     * A setter method for the isMarked field.
     *
     * @param isMarked the boolean value to be set
     */
    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    /**
     * Helper method to stringify LocalDateTime objects into original text string
     * pass by the user.
     *
     * @param dateTime the LocalDateTime object to be stringified
     * @return text string of the LocalDateTime object
     */
    public String stringifyDate(LocalDateTime dateTime) {
        String formatted = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm"));
        return String.join(" ", formatted.split("T"));
    }

    /**
     * Helper method to stringify LocalDateTime objects into user-friendly date format.
     *
     * @param dateTime the LocalDateTime object to be stringified
     * @return text string of the formatted date
     */
    public String formatDate(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("HHmm, MMM d yyyy"));
    }

    /**
     * String representation of the Task object.
     *
     * @return The string representation
     */
    @Override
    public String toString() {
        String typeLetter = this.type.substring(0, 1).toUpperCase();
        return String.format("[%s][%s] %s", typeLetter, this.getStatusIcon(), this.description);
    }

    /**
     * Checks if an object is equal to the task.
     *
     * @param o the object to be checked
     * @return boolean value if the objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof Task) {
            Task t = (Task) o;
            return this.isMarked == t.isMarked && this.description.equals(t.description);
        }

        return false;
    }

    /**
     * Returns a string form of whether the Task is marked.
     *
     * @return string representation of the isMarked value
     */
    private String getStatusIcon() {
        return (isMarked ? "X" : " ");
    }
}
