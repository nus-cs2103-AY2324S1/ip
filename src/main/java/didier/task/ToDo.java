package didier.task;

/**
 * A ToDo is the most basic type of Task. It only needs to be supplied with its description.
 */
public class ToDo extends Task {

    /**
     * The constructor for the ToDo object. The todo is marked as undone by default at the start.
     *
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * The constructor for the ToDo object that allows the user to specify whether the ToDo is done or
     * undone initially.
     * @param description The description of the todo task.
     * @param isDone Whether the todo is done or not.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String composeToFileString() {
        return String.format("T|%s", super.composeToFileString());
    }
}
