package hachi;

import java.time.LocalDate;

/**
 * Represents the individual tasks in the task list.
 * Includes their completion status, name, and various methods.
 */
public class Task {
    private boolean completed = false;
    private String taskName = "";

    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.completed = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        this.completed = false;
    }

    /**
     * Method to be overriden by tasks that have dates. For tasks without, always returns false.
     *
     * @return false
     */
    public boolean isDateWithinRange(LocalDate date) {
        return false;
    }

    /**
     * Converts Task object to its string representation when stored in the hard drive.
     *
     * @return String representation when stored in text file on user's hard drive
     */
    public String toData() {
        return (completed ? "1" : "0") + " | " + taskName;
    }

    @Override
    public String toString() {
        String checkbox = "[" + (completed ? "X" : " ") + "]";
        return checkbox + " " + taskName;
    }
}