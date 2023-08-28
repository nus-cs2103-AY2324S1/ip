/**
 * Represents a todo.
 *
 * @author Pearlynn
 */

public class Todo extends Task {

    /**
     * Constructor for Todo class.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo.
     *
     * @return A string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
