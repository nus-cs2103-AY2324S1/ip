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

    public Todo(String bool, String description) {
        super(description.strip());
        if (Integer.parseInt(bool) == 1) {
            this.isDone = true;
        }
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

    /**
     * Returns the string representing the task to be written in the disk.
     *
     * @return The string describing this task to be written in the disk.
     */
    @Override
    public String toStringForFile() {
        return "T | " + super.toStringForFile();
    }
}
