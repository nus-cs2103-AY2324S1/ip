package prts.task;

/**
 * Represents a to-do task, with a description of the task to be done.
 */
public class Todo extends Task {

    private String addMessage = "Understood. No rest for the weary, eh?";

    /**
     * Constructs a to-do object, given its description.
     * @param name The description of the to-do object, as will be represented in the list of tasks.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns the string representation of the to-do object.
     * @return A string representation for display to the user.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a message to display to the user when this type of Task is added to the TaskList.
     * @return The message to display to the user.
     */
    public String getAddMessage() {
        return addMessage;
    }

}
