package yong.tasks;

/**
 * duke.tasks.ToDo class representing a task and description
 */
public class ToDo extends Task {

    /**
     * Constructor for the ToDo class.
     *
     * @param description Description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * String representation of the ToDo task.
     *
     * @return Information of event
     */
    @Override
    public String toString() {
        String ret = "[T] " + super.toString();

        return ret;
    }
}
