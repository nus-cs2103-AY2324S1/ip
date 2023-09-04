package tasks;

import tasks.Task;

/**
 * This class encapsulates a ToDo child class
 * that contains a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object.
     *
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }


    /**
     * Constructs a ToDo object that specifies whether it has been completed.
     *
     * @param description
     * @param isDone
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toText() {
        return "T " + this.getDoneStatus() + " " + this.description;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
