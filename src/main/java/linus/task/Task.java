package linus.task;

import linus.util.Ui;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
        Ui.print("Nice! I've marked this linus.task as done:\n"
                + "[X] "
                + this.description
        );
    }

    public void unmark() {
        this.isDone = false;
        Ui.print(
                "OK, I've marked this linus.task as not done yet:\n"
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
