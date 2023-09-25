package duke;

import java.util.*;

/**
 * The Task class creates generic Task objects. It is made to be concrete
 * instead of abstract to facilitate debugging.
 */
public class Task {
    private static final String DONE_MARKER = "[X] ";
    private static final String UNDONE_MARKER = "[  ] ";
    private static final String DELIMITER = "|";
    boolean isDone;
    String name;

    Task(boolean isDone, String name) {
        this.isDone = isDone;
        this.name = name;
    }

    /*
     * Create a duke.Task object with default false
     * done status
     */
    Task(String name) {
        this(false, name);
    }

    /**
     * Returns an empty String. This method exists to facilitate
     * polymorphic behaviour in inherited child classes, and also
     * for debugging purposes, which is why it remains a non-abstract
     * class.
     *
     * @return The String representation of the type of the Task
     */
    public String taskType() {
        return "";
    }

    /**
     * Marks the Task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the Task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a String representation of the task that includes its name
     * and also its completion status with an "X".
     *
     * @return The String representation of the Task.
     */
    @Override
    public String toString() {
        assert (this.name != null);
        if (isDone) {
            return DONE_MARKER + this.name;
        } else {
            return UNDONE_MARKER + this.name;
        }
    }

    /**
     * Returns a Storage-compatible String representation of the Task.
     * This String contains all information about the task that can be stored
     * and later retrieved without any loss in information.
     *
     * @return The Storage-compatible String representation of the Task.
     */
    public String fileToString() {
        String finalOut = "";
        finalOut += this.taskType() + DELIMITER;
        finalOut += this.isDone ? "1" : "0";
        finalOut += DELIMITER;
        finalOut += this.name;

        return finalOut;
    }

}

