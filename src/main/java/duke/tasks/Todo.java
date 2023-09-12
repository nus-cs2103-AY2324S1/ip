package duke.tasks;

/**
 * Todo is a subclass of a Task
 * Takes in the description and returns a todo
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * toString method to return the todo object in a string
     * @return string for todo
     */
    @Override
    public String toString() {
        return String.format("| T |%s", super.toString());
    }
}
