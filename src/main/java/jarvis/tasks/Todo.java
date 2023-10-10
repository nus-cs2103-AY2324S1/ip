package jarvis.tasks;

/**
 * Represents the "Todo" task in Jarvis app.
 */
public class Todo extends Task {

    /**
     * Initializes a new instance of the Todo task.
     *
     * @param title       The title or description of the todo task.
     * @param isCompleted A boolean indicating whether the todo task is completed or not.
     */
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
