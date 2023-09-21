package com.ducky.task;

import java.time.LocalDate;

/**
 * Represents a task stored in Ducky chatbot memory.
 */
public abstract class Task {

    private final String description;
    private boolean isComplete;

    /**
     * Constructs a task with specified description and incomplete by default.
     * @param desc Description of task.
     */
    public Task(String desc) {
        this.description = desc;
        this.isComplete = false;
    }

    /**
     * Sets the task status to complete.
     */
    public void setComplete() {
        this.isComplete = true;
    }

    /**
     * Sets the task status to incomplete.
     */
    public void setIncomplete() {
        this.isComplete = false;
    }

    /**
     * Returns whether task description contains the given string as a substring.
     * @param s String to check in description.
     * @return True if description contains specified string.
     */
    public boolean containsString(String s) {
        return this.description.contains(s);
    }

    /**
     * Returns whether task falls on the specified date.
     * @param queryDate Date to check against the task's date.
     * @return True if task falls on the specified date.
     */
    public boolean dateFallsOn(LocalDate queryDate) {
        return false;
    }

    /**
     * Returns a representation of the task for printing to user interface.
     * @return Representation of the task meant for printing.
     */
    @Override
    public String toString() {
        if (this.isComplete) {
            return String.format("[X] %s", this.description);
        }
        return String.format("[  ] %s", this.description);
    }

    /**
     * Returns a representation of the task for saving to file system.
     * @return Representation of the task meant for saving.
     */
    public String getSaveFormat() {
        return String.format(
                "%d | %s",
                this.isComplete ? 1 : 0,
                this.description
        );
    }
}
