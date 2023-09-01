package Duke.Tasks;

/**
 * Encapsulates a specific task of type Todo.
 *
 * Does not have any special characteristics.
 */
public class TodoTask extends Task {

    /**
     * Constructor for a Todo task
     *
     * @param itemName The name of the Todo
     */
    public TodoTask(String itemName) {
        super(itemName);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
