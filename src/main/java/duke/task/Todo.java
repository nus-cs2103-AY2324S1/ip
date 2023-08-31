package duke.task;

/**
 * A Todo class to represent todo tasks
 */
public class Todo extends Task {

    /**
     * Constructs todo with the given description
     *
     * @param description the given description of the todo
     * @param isDone whether the todo is done
     *
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }

    /**
     * Returns String representing the todo
     *
     * @return String representing todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}