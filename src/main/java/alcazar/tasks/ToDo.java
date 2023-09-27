package alcazar.tasks;

/**
 * Encapsulates a ToDo Task
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo task
     * @param description The input description of this ToDo
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Evaluates String form of a ToDo task
     * @return String form of a ToDo Task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}