package duke.task;

import java.time.LocalDate;

/**
 * An Abstraction that represents the task object.
 * Inherit this class to create different kinds of task
 */
public abstract class Task {

    protected final String task;
    protected boolean isDone;
    protected String location;

    protected Task(String task, String location) {
        assert task != null : "task input cannot be empty";
        this.task = task;
        this.isDone = false;
        this.location = location;
    }

    /**
     * Marks the task as isDone.
     */
    public void doTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as not isDone.
     */
    public void undoTask() {
        this.isDone = false;
    }

    /**
     * Checks if the task is on a given date.
     *
     * @param date the date to check on.
     * @return true if date matches task date.
     */
    public abstract boolean isOnDate(LocalDate date);

    /**
     * Converts the task to the correct format to save to storage/file.
     * Format: "isDone | task description", can be added with task type for subtypes.
     *
     * @return task string in correct format.
     */
    public String toSaveFormat() {
        return (this.isDone ? 1 : 0) + "|" + this.task + "|" + this.location;
    }

    public boolean containsDescription(String description) {
        return this.task.contains(description);
    }

    public boolean containsLocation(String location) {
        return this.location.contains(location);
    }

    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return String.format("[%s] %s [at: %s]", mark, this.task, this.location);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task o = (Task) other;
        return this.isDone == o.isDone && this.task.equals(o.task);
    }
}
