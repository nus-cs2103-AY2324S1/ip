package duke;

import java.io.Serializable;

/**
 * can be considered an abstract class. Can be either a Todo, Deadline, or Event class
 */
public class Task implements Serializable {
    private boolean marked;
    private String description;

    /**
     * Constructor to initialise a Task object
     * @param description the Task description that is obtained from the user input
     */
    public Task(String description) {
        this.description = description;
        this.marked = false;
    }
    public String getStatusIconWithBracket() {
        return marked ? "[X]" : "[ ]";
    }
    public String getStatusIcon() {
        return marked ? "X" : "O";
    }
    public void markAsDone() {
        this.marked = true;
    }
    public String getDescription() {
        return this.description;
    }
    public void markAsUnDone() {
        this.marked = false;
    }
    @Override
    public String toString() {
        return this.getStatusIconWithBracket() + " " + description;
    }
    public String toFileString() {
        return " | " + this.getStatusIcon() + " | " + this.getDescription();
    }

}
