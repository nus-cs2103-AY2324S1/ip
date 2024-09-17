package duke.tasks;

/**
 * The ToDo class represents a simple task without any specific time or date associated.
 * It is a subclass of the Task class and provides methods to work with ToDo tasks.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given task name.
     *
     * @param taskName The name of the ToDo task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Constructs a ToDo task with the given task name and completion status.
     *
     * @param taskName The name of the ToDo task.
     * @param done The completion status of the task.
     */
    public ToDo(String taskName, boolean done) {
        super(taskName, done);
    }

    /**
     * Overrides the toString() method to provide a custom string representation of the ToDo task.
     *
     * @return A formatted string containing task details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
