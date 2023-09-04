package jarvis.tasks;

/**
 * Represents the "Todo" task in Jarvis app.
 */
public class Todo extends Task {
    public Todo(String title, boolean isCompleted) {
        super(title, isCompleted);
    }

    /**
     * Overrides the toString method to provide a custom string representation of the Todo task.
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "T | " + super.toString();
    }
}
