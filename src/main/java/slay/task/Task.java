package slay.task;

import slay.exception.DuplicatedMarkException;

/**
 * An abstract class representing a Task.
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;
    protected String tag;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.tag = null;
    }

    public Task(String name, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Returns String representation of the status of this Task.
     *
     * @return "X" if the Task is marked as done or " " if the Task is marked as not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return this.tag == null ? "[" + statusIcon + "]" + "[#] "+ this.name
                : "[" + statusIcon + "]" + "[#" + this.tag + "] "+ this.name;
    }

    public String getDescription() {
        return this.name;
    }

    public abstract String getEncodedString();

    public static ToDo getDecodedToDo(String encodedString) {
        String[] args = encodedString.split(" \\| ");
        Boolean isDone = args[1] == "1" ? true : false;
        String tag = args[2];
        ToDo todo = new ToDo(isDone, args[3]);
        if (!tag.equals("null")) {
            todo.tag(tag);
        }
        return todo;
    }

    public static Deadline getDecodedDeadline(String encodedString) {
        String[] args = encodedString.split(" \\| ");
        Boolean isDone = args[1] == "1" ? true : false;
        String tag = args[2];
        Deadline deadline = new Deadline(isDone, args[3], args[4]);
        if (!tag.equals("null")) {
            deadline.tag(tag);
        }
        return deadline;
    }

    public static Event getDecodedEvent(String encodedString) {
        String[] args = encodedString.split(" \\| ");
        Boolean isDone = args[1] == "1" ? true : false;
        String tag = args[2];
        Event event = new Event(isDone, args[3], args[4], args[5]);
        if (!tag.equals("null")) {
            event.tag(tag);
        }
        return event;
    }

    /**
     * Marks this Task as done.
     *
     * @throws DuplicatedMarkException if the Task is currently marked as done.
     */
    public void markAsDone() throws DuplicatedMarkException {
        if (this.isDone) {
            throw new DuplicatedMarkException("The task has already been marked as done.");
        } else {
            this.isDone = true;
        }
    }

    /**
     * Marks this Task as not done.
     *
     * @throws DuplicatedMarkException if the Task is currently marked as not done.
     */
    public void markAsNotDone() throws DuplicatedMarkException {
        if (!this.isDone) {
            throw new DuplicatedMarkException("The task is currently undone.");
        } else {
            this.isDone = false;
        }
    }

    public void tag(String tag) {
        this.tag = tag;
    }

    /**
     * Returns the tag of this Task.
     *
     * @return String of the tag or null if the Task is not tagged.
     */
    public String getTag() {
        return this.tag;
    }
}
