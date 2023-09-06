package tong.task;

import tong.task.Deadline;
import tong.task.Event;
import tong.task.ToDo;
import tong.exception.DuplicatedMarkException;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return "[" + statusIcon + "] " + this.name;
    }

    public String getDescription() {
        return this.name;
    }

    public abstract String getEncodedString();

    public static ToDo getDecodedToDo(String encodedString) {
        String[] args = encodedString.split(" \\| ");
        Boolean isDone = args[1] == "1" ? true : false;
        return new ToDo(isDone, args[2]);
    }

    public static Deadline getDecodedDeadline(String encodedString) {
        String[] args = encodedString.split(" \\| ");
        Boolean isDone = args[1] == "1" ? true : false;
        return new Deadline(isDone, args[2], args[3]);
    }

    public static Event getDecodedEvent(String encodedString) {
        String[] args = encodedString.split(" \\| ");
        Boolean isDone = args[1] == "1" ? true : false;
        return new Event(isDone, args[2], args[3], args[4]);
    }

    public void markAsDone() throws DuplicatedMarkException {
        if (this.isDone) {
            throw new DuplicatedMarkException("The task has already been marked as done.");
        } else {
            this.isDone = true;
        }
    }

    public void markAsNotDone() throws DuplicatedMarkException {
        if (!this.isDone) {
            throw new DuplicatedMarkException("The task is currently undone.");
        } else {
            this.isDone = false;
        }
    }
}
