package duke.task;


/**
 * The ToDo class that inherits from Task.
 */
public class ToDo extends Task {



    /**
     * Constructor with description.
     * @param description the description of the todo being added.
     */

    public ToDo(String description) {
        super(description);

    }

    /**
     * Constructor with description and whether it has been marked as done.
     * @param description the description of the todo being added.
     * @param isDone whether it has been marked as done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);

    }


    /**
     * Returns the toString representation of a todo task.
     * @return the String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    /**
     * Returns the String representation of the task that will be written to the
     * text file.
     * @return The String representation of the task for the text file.
     */
    @Override
    public String toWriteString() {
        return "T | " + (isDone ? "X" : "0") + " | " + description;
    }

}
