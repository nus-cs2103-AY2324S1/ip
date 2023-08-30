package duke.task;

import duke.DukeException;

public abstract class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

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

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.name);
    }

    public String exportToText() {
        return String.format("%s,>%s", this.name, this.isDone ? "X" : "O");
    }
}
