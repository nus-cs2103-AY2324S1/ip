package com.ducky.task;

/**
 * Represents a task with only a description.
 */
public class TodoTask extends Task {

    /**
     * Constructs a task with the specified description.
     * @param desc Description of task.
     */
    public TodoTask(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String getSaveFormat() {
        return String.format("T | %s", super.getSaveFormat());
    }
}
