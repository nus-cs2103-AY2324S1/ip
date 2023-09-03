package duke;

import java.util.*;

/**
 * The Task class creates generic Task objects. It is made to be concrete
 * instead of abstract to facilitate debugging.
 */
public class Task {
    boolean done;
    String name;

    Task(boolean done, String name) {
        this.done = done;
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
     * polymorphic behaviour.
     */
    public String taskType() {
        return " ";
    }

    /**
     * Marks the Task as done.
     */
    public void mark() {
        this.done = true;
        }

    /**
     * Marks the Task as undone.
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Returns a String representation of the task that includes its name
     * and also its completion status with an "X".
     *
     * @return The String representation of the Task.
     */
    @Override
    public String toString() {
        if (done) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    /**
     * Returns a File-compatible String representation of the Task.
     * This String contains all information about the task that can be stored
     * and later retrieved without any loss in information.
     *
     * @return The File-compatible String representation of the Task.
     */
    public String fileToString() {
        String finalOut = "";
        finalOut += this.taskType() + "|";
        finalOut += this.done ? "1" : "0";
        finalOut += "|";
        finalOut += this.name;

        return finalOut;
    }
}

