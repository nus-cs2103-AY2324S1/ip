package tasks;

/**
 * Todo task, a type of task without any date/time attached to it.
 */
public class ToDo extends Task {
    
    /**
     * Initializes a new todo task with the given description. The task's initial status is set to not done.
     *
     * @param desc The description of the todo task
     */
    public ToDo(String desc) {
        super(desc);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return Task type, status icon and description of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
