package aichan.task;

/**
 * Represents a task that user need to do.
 * Inherits from Task.
 */
public class ToDo extends Task{

    /**
     * Constructs the ToDo object.
     * Initializes description, set it as have not done.
     *
     * @param str Description of the ToDo.
     */
    public ToDo(String str) {
        super(str);
    }

    /**
     * Returns the string representation of the todo object.
     *
     * @return A string indicates whether the todo is done followed by its description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the line of the todo to be saved in the file.
     *
     * @return A string indicates whether the todo is done followed by its description.
     */
    @Override
    public String toFileLine() {
        return String.format("T | %s", super.toFileLine());
    }
}
