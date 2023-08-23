/**
 * Represents a todo task, a type of task without a specific deadline or duration.
 */
public class Todo extends Task{
    private String type = "T";

    /**
     * Creates a new todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(TaskType.TODO, description);
    }

}
