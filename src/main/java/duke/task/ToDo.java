package duke.task;

/**
 * The ToDo class represents a to-do task in a task management application.
 * It extends the Task class and inherits its properties and methods.
 */
public class ToDo extends Task{

    /**
     * Constructs a new ToDo object with the given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the ToDo task into a string representation suitable for saving to a text file.
     *
     * @return A string in the format "[T] | [X]" if the task is done, or "[T] | [ ]" if the task is not done,
     *         followed by the task description.
     */
    @Override
    public String toTxtString() {
        return "[T] | [" + (this.isDone ? "X" : " ") + "] | " + this.description;

    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string in the format "[T]" followed by the result of calling the toString() method of the superclass (Task).
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
