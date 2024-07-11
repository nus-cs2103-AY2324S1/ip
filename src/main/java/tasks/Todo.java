package tasks;

/**
 * Represents a todo task that can be tracked in
 * the chatbot.
 */
public class Todo extends Task {

    /**
     * Todo Constructor that takes in a String.
     * @param desc Description of the todo.
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * Returns the string representation of a todo.
     * @return The string representation of a todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
