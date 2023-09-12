package duke.tasks;

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

    /**
     * Constructor for a Todo task
     *
     * @param itemName The name of the Todo
     * @param id the saved item id
     */
    public TodoTask(int id, String itemName) {
        super(id, itemName);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
