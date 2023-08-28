package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;

public class Task {
    protected String description;
    protected boolean isDone;

    protected static String getDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("h:mm a, MMM d yyyy"));
    }

    public Task(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("\u2639 OOPS!!! The description of a task cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("\u2639 OOPS!!! The description of a task cannot be empty.");
        }
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toFileString() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }
}