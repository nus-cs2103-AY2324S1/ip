package duke.task;


/**
 * Represents the Todo task.
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with a specified description.
     *
     * @param description A string describing the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a String representing the Todo task that will be stored.
     *
     * @return the string representing the Todo task that will be stored.
     */
    @Override
    public String getInput() {
        return "T | " + this.getStatusIcon() + " | " + this.description;
    }

    /**
     * Returns the String representation of a Todo task.
     *
     * @return the String representation of a Todo Task.
     */
    @Override
    public String toString() {
        return "[T] [" + this.getStatusIcon() + "] " + this.description;
    }
}
