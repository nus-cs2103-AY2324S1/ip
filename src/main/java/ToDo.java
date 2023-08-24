public class ToDo extends Task {

    /**
     * Constructor to create a ToDo object.
     *
     * @param description The task description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Method to get the string representation of the todo task.
     *
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
