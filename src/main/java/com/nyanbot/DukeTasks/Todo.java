package com.nyanbot.DukeTasks;

/**
 * Encapsulates a class which represents a task to be done.
 *
 * @author Tan Kerway
 */
public class Todo extends Task {

    /**
     * Constructor for the class.
     *
     * @author Tan Kerway
     * @param description the task description to be added
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for the class.
     *
     * @author Tan Kerway
     * @param description the task description to be added
     * @param isDone whether the task is done
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of the class.
     * Adds the "[T]" tag to the default task toString.
     *
     * @author Tan Kerway
     * @return the string representation of the class
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
