package task;

/**
 * Object class for instances of todo tasks
 */
public class ToDo extends Task {

    /**
     * Public constructor of the class
     * @param description description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overloaded constructor to create classes with known status
     * @param status done status of task
     * @param description description of the task
     */
    public ToDo(boolean status, String description) {
        super(status, description);
    }

    /**
     * Overriden toString implementation for a todo task
     * @return String representing a todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * abstract declaration of a method to format properties of the todo task
     * @return String of formatted data of the instance
     */
    @Override
    public String dataFormat() {
        return "todo/"
                + isDone.toString() + "/"
                + description;
    }
}
