package com.mimi.tasks;

/**
 * The parent class of all Tasks. Represents some basic information common to all Tasks.
 * @author Yuheng
 */
public class Task {

    private String taskName;
    private boolean isDone = false;

    /**
     * Creates an instance of a Task.
     * @param taskName the string representation of the task name.
     */
    Task(String taskName) {
        this.taskName = taskName;
    }


    /**
     * Toggles the task between marked done or not done.
     */
    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns the string representation of the task status.
     * @return a string that represents the status of the task based on whether it has been marked as done.
     */

    public String getStatusIcon() {
        return this.isDone ? "X" : "";
    }

    /**
     * Returns true if the task is done and false otherwise.
     * @return a boolean value that represents if the task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the string representation of the task type.
     * @return a string that represents what kind of task this is.
     */
    public String eventCode() {
        return "";
    }

    /**
     * Gives a string representation of the task name.
     * @return a string that represents the task name.
     */
    public String taskName() {
        return this.taskName;
    }

    /**
     * A string representation of the start time of the task, if any.
     * @return a formatted string that shows the start time of the task.
     */
    public String taskStartTime() {
        return "";
    }

    /**
     * A string representation of the end time of the task, if any.
     * @return a formatted string that shows the end time of the task.
     */
    public String taskEndTime() {
        return "";
    }

    /**
     * A string that is used to store the task content into the hard drive
     * @return string representation of the task for storage.
     */
    public String writeFormat() {
        return this.taskName();
    }
}
