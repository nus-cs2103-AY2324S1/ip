package duke.tasks;

import duke.Exceptions.IncompleteDescriptionException;

abstract public class Task {
    protected boolean isDone;
    protected String taskName;

    protected Task(String taskName) throws IncompleteDescriptionException {
        if (taskName.isBlank()) throw new IncompleteDescriptionException();
        isDone = false;
        this.taskName = taskName;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unMarkDone() {
        this.isDone = false;
    }

    public abstract String compressData();

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
