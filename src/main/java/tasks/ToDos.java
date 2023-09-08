package tasks;

/**
 * The ToDos class stores the description of a todos task.
 *
 * @author Kenvyn Kwek
 */
public class ToDos extends Task {
    /**
     * Initializes a todos task.
     *
     * @param description Description of the todos task.
     * @param isDone If the task has already been completed.
     */
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * String representation of the todos task.
     *
     * @return Details of the todos task.
     */
    @Override
    public String toString() {
        return "T | " + super.toString();
    }
}
