package duke.task;

import duke.DukeException;

/**
 * Represents a task.
 */
public abstract class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructor for Task.
     * @param name
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructor for Task with specified isDone.
     * @param name
     * @param isDone
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public boolean isToday(String dateStr) throws DukeException {
        return false;
    }

    public boolean contains(String keyword) {
        return this.name.contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.name);
    }

    public String exportToText() {
        return String.format("%s,>%s", this.name, this.isDone ? "X" : "O");
    }
}
