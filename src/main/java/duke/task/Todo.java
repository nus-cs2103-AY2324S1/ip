package duke.task;

/**
 * This class encapsulates a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructor method for a Todo.
     * @param taskDescription Description of the task
     */
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
