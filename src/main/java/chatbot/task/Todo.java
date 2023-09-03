package chatbot.task;

import chatbot.task.Task;

public class Todo extends Task {
    /** Constructor for chatbot.task.Todo.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /** toString method for todo.
     *
     * @return String representation of todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
