package duke.tasks;

import duke.tasks.Task;

/**
 * duke.tasks.ToDo class representing a task and description
 */
public class ToDo extends Task {

    /**
     * Constructor for the duke.tasks.ToDo class
     * @param description Description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * String representation of the duke.tasks.ToDo task
     * @return Information of event
     */
    @Override
    public String toString() {
        String ret = "[T] " + super.toString();

        return ret;
    }
}
