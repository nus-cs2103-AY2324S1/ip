package Jeoe.Tasks;

/**
 * This class encapsulates the Todo class.
 * It represents a task that has only its description.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo object.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
