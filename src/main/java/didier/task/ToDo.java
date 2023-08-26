package didier.task;

/**
 * A didier.task.ToDo is the most basic type of didier.task.Task. It only needs to be supplied with its description.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

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
