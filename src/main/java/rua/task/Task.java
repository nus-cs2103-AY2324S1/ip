package rua.task;

import java.time.LocalDate;

public class Task {
    protected final String description;
    protected final Boolean marked;

    Task(String description, Boolean marked) {
        this.description = description;
        this.marked = marked;
    }

    Task(String description) {
        this.description = description;
        this.marked = false;
    }

    public String getType() {
        return "None";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean isMarked() {
        return this.marked;
    }

    public Boolean happenOnThatDate(LocalDate date) {
        return false;
    }

    public Task mark() {
        return new Task(this.description, true);
    }

    public Task unmark() {
        return new Task(this.description, false);
    }
    @Override
    public String toString() {
        return "[" +  (this.marked ? "X" : " ") + "] " + this.description;
    }
}
