package Chatbot.alain;

/**
 * Represents a task of type "To-Do" without a specific deadline or time.
 */
public class ToDos extends Task {

    /**
     * Constructs a To-Do task.
     *
     * @param description Description of the to-do task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the To-Do task.
     *
     * @return String representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

