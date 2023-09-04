package linus.task;

import linus.util.Ui;

/**
 * Represents a Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
        Ui.print("Nice! I've marked this task as done:\n"
                + "[X] "
                + this.description
        );
    }

    /**
     * Unmarks the task as done.
     */
    public void unmark() {
        this.isDone = false;
        Ui.print(
                "OK, I've marked this task as not done yet:\n"
                        + "[ ] "
                        + this.description
        );
    }


    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done linus.task with X
    }

    public abstract String getTaskTypeIcon();

    @Override
    public String toString() {
        return this.getTaskTypeIcon()
                + this.getStatusIcon() + " "
                + this.description;
    }
}
