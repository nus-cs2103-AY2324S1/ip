package chatty.task;

/**
 * Class that handles the todo task
 */
public class ToDo extends Task {

    /**
     * Constructor for the ToDo class that calls the constructor of the parent class to create a Task object.
     *
     * @param task The description of the task to be added.
     */
    public ToDo(String task) {
        super(task);
    }

    /**
     * Returns the string representation of the ToDo task, including its status and description.
     *
     * @return A String representation of the task, including whether it is done or not and its description.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[T][X] " + this.task;
        } else {
            return "[T][ ] " + this.task;
        }
    }
}
