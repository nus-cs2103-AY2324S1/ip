package alpha;

/**
 * Class representation of a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo instance.
     *
     * @param description The description of the task.
     * @return A new ToDo instance.
     */
    public static ToDo createToDo(String description) {
        return new ToDo(description);
    }

    private ToDo(String description) {
        super(description.trim());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
