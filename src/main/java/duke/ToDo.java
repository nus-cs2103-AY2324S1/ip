package duke;

/**
 * The ToDo class represents a task of type "ToDo" that the user wants to do.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified task name.
     *
     * @param taskName The name of the ToDo task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Retrieves the type of the task, which is "T" for ToDo.
     *
     * @return The task type.
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the ToDo task.
     */
    @Override
    public String getDescription() {
        return super.toString();
    }

    /**
     * Generates a formatted string representation of the task's status and description.
     *
     * @return A formatted string displaying the status and description of the ToDo task.
     */
    @Override
    public String statusAndTask() {
        return "[T]" + statusString() + " " + super.toString();
    }
}
