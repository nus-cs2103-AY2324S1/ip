/**
 * Represents a Todo task
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     * 
     * @param description The description of the todo task.
     * @param isDone      Whether the todo task is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }
    
    /**
     * Returns the description of the todo task, to output to the user.
     * 
     * @return A String, properly formatting the description of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the isDone, and description of the todo task, to input into the tasks.txt file.
     * 
     * @return A String, properly formatting the description of the todo task.
     */
    public String toFileString() {
        // Convert task to file format string
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
    
}
