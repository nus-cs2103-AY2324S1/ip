package duke;

/** Abstraction of a task that has no deadline */
public class ToDo extends Task {

    /** Creates a task with no deadline
     *
     * @param description What the task is about.
     * @param isDone whether the task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getStorageDescription() {
        String isCompleted = this.isDone ? "1" : "0";
        return "T " + isCompleted + " " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
