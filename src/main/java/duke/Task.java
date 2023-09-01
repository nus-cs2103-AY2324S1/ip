package duke;

import java.time.LocalDate;

public abstract class Task {

    protected final String task;
    protected boolean done;

    protected Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Marks the task as done.
     */
    public void doTask() {
        this.done = true;
    }

    /**
     * Marks the task as not done.
     */
    public void undoTask() {
        this.done = false;
    }

    /**
     * Checks if the task is on a given date.
     *
     * @param date the date to check on.
     * @return true if date matches task date.
     */
    public abstract boolean isOnDate(LocalDate date);

    // returns "done | task description", can be added with task type for subtypes

    /**
     * Converts the task to the correct format to save to storage/file.
     *
     * @return task string in correct format.
     */
    public String toSaveFormat() {
        return (this.done ? 1 : 0) + " | " + this.task;
    }

    public boolean containsDescription(String description) {
        return this.task.contains(description);
    }

    @Override
    public String toString() {
        String mark = done ? "X" : " ";
        return String.format("[%s] %s", mark, this.task);
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
        return this.done == o.done && this.task.equals(o.task);
    }
}
