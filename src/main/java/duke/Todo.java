package duke;

/**
 * Encapsulation of a Todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     * @param todoName Name of todo.
     */
    public Todo(String todoName) {
        super(todoName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String serialize() {
        return String.format(
                "todo %s%s",
                getTaskName(),
                (this.isDone() ? " /done" : "")
        );
    }
}
