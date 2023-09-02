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

    /** toString method for chatbot.task.Todo.
     *
     * @return String representation of chatbot.task.Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
