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

    public String getDescription() {
        return this.description;
    }

    public Boolean isMarked() {
        return this.marked;
    }

    public Boolean isHappeningOnThatDate(LocalDate date) {
        return false;
    }

    public Task mark() {
        return new Task(this.description, true);
    }

    public Task unmark() {
        return new Task(this.description, false);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }
        Task c = (Task) o;

        // Compare the data members and return accordingly
        return c.description.equals(this.description) &&
                c.marked.equals(this.marked);
    }

    @Override
    public String toString() {
        return "[" + (this.marked ? "X" : " ") + "] " + this.description;
    }
}
