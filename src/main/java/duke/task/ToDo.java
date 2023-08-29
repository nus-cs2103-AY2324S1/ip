package duke.task;

/**
 * Represents a todo task in the chatbot.
 */
public class ToDo extends Task {
    /**
     * Constructor for a todo task.
     * 
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getOutputString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
