/**
 * The class that represents a todo task.
 *
 * @author Zhong Han
 */
public class Todo extends Task {

    /**
     * Constructor of a todo task.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description.strip());
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return a string comprising the symbol, status
     *      and the description of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
