package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task entered by a user.
 */
public abstract class Task {
    private final String description;
    private boolean isMarked;
    public final String type;

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
    abstract public String getOriginalMessage();

    public String getDescription() {
        return this.description;
    }

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

    public String stringifyDate(LocalDateTime dateTime) {
        String formatted = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm"));
        return String.join(" ", formatted.split("T"));
    }

    public String formatDate(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("HHmm, MMM d yyyy"));
    }

    @Override
    public String toString() {
        String typeLetter = this.type.substring(0,1).toUpperCase();
        return String.format("[%s][%s] %s", typeLetter, this.getStatusIcon(), this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof Task) {
            Task t = (Task) o;
            return this.isMarked == t.isMarked && this.description.equals(t.description);
        }

        return false;
    }

    private String getStatusIcon() {
        return (isMarked ? "X" : " ");
    }
}
