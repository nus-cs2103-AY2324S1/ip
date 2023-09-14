package chatty.task;

/**
 * Class that handles the todo task
 */
public class ToDo extends Task {

    /**
     * Contructor for todo class that calls the constructor of the parent class to create a Task object
     * @param task the task to be added
     */
    public ToDo(String task) {
        super(task);
    }

    /**
     * Return the String representation of the object
     * @return String representation of the object
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
