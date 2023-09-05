package hachi;

import java.time.LocalDate;

public class Task {
    private boolean completed = false;
    private String taskName = "";

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void mark() {
        this.completed = true;
    }

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
     * Returns true if string is within the task name.
     *
     * @return true if string is within the task name.
     */
    public boolean isStringWithinTaskName(String str) {
        return taskName.contains(str);
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