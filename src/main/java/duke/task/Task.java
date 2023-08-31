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
     * Instantiates the task with the given name.
     * @param name content of the task
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
     * @return whether the task has been done
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Converts the current task to a string that can be stored in the disk.
     * @return the string to be stored in disk
     */
    public String data() {
        return (this.isDone ? "1 " : "0 ") + this.name;
    }

    /**
     * Checks whether this task "contains" the date.
     * Definition of "contains" to be decided by child classes.
     * @param dateTime the datetime to check against
     * @return whether this task contains the date
     */
    public boolean containsDate(LocalDate dateTime) {
        return false;
    }

    /**
     * String representation of this task, to be printed in UI.
     * @return the string representation to be printed in UI
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    /**
     * Checks whether this task is the same as another task, for testing purposes.
     * @param another the object to compare with
     * @return whether this task is the same as the given task
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
