package duke.task;

import java.time.LocalDate;

/**
 * Represent a task that the user requested to add.
 * Three possible types of task: todo, deadline and event.
 * This is a base class, to be inherited.
 */
public abstract class Task {
    private String name;
    private boolean isDone = false;

    /**
     * All possible types.
     * Default value is used if the variable does not need a specification of task type.
     */
    public enum Type {
        TODO,
        DEADLINE,
        EVENT,
        DEFAULT,
    }

    /**
     * Instantiates the task with the given name.
     * @param name Content of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks the task as having been done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as having not been done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Checks whether the task has been done.
     * @return Whether the task has been done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Converts the current task to a string that can be stored in the disk.
     * @return The string to be stored in disk.
     */
    public String getData() {
        return (this.isDone ? "1 " : "0 ") + this.name;
    }

    /**
     * Checks whether this task "contains" the date.
     * Definition of "contains" to be decided by child classes.
     * @param dateTime The datetime to check against.
     * @return Whether this task contains the date.
     */
    public boolean containsDate(LocalDate dateTime) {
        return false;
    }

    /**
     * Checks if this task content contains the input.
     * @param string The input to check against.
     * @return Whether this task content contains the input.
     */
    public boolean containsString(String string) {
        return this.name.contains(string);
    }

    /**
     * Update this task name.
     * @param newName The new name of this task.
     */
    public void updateName(String newName) {
        this.name = newName;
    }

    /**
     * String representation of this task, to be printed in UI.
     * @return The string representation to be printed in UI.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    /**
     * Checks whether this task is the same as another task, for testing purposes.
     * @param another The object to compare with.
     * @return Whether this task is the same as the given task.
     */
    @Override
    public boolean equals(Object another) {
        if (another instanceof Task) {
            Task anotherTask = (Task) another;
            return this.name.equals(anotherTask.name)
                    && this.isDone == anotherTask.isDone;
        }
        return false;
    }
}
