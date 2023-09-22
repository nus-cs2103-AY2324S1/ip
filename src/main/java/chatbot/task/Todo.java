package chatbot.task;

public class Todo extends Task {
    /**
     * Constructor for chatbot.task.Todo.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for task with tag loaded from Storage.
     *
     * @param description Description of task.
     * @param tag         Tag of task.
     */
    public Todo(String description, String tag) {
        super(description, tag);
    }

    /**
     * toString method for todo.
     *
     * @return String representation of todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
