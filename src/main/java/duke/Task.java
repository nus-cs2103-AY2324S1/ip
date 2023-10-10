package duke;

import java.io.Serializable;

/**
 * Can be either a Todo, Deadline, or Event object
 */
public abstract class Task implements Serializable {
    private boolean isMarked;
    private String description;

    /**
     * Constructor to initialise a Task object
     * @param description the Task description that is obtained from the user input
     */
    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }
    public String getStatusIconWithBracket() {
        return isMarked ? "[X]" : "[ ]";
    }
    public String getStatusIcon() {
        return isMarked ? "X" : "O";
    }
    public void markAsDone() {
        this.isMarked = true;
    }
    public String getDescription() {
        return this.description;
    }
    public void markAsUnDone() {
        this.isMarked = false;
    }
    @Override
    public String toString() {
        return this.getStatusIconWithBracket() + " " + description;
    }
    public String toFileString() {
        return " | " + this.getStatusIcon() + " | " + this.getDescription();
    }

}
