package duke.task;

import java.time.LocalDate;

public abstract class Task {
    private String name;
    private boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String data() {
        return (this.isDone ? "1 " : "0 ") + this.name;
    }

    public boolean containsDate(LocalDate dateTime) {
        return false;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }
}
