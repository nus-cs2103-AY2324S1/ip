/**
 * Represents a task without any date or time attached to it.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class ToDo extends Task {
    /**
     * A constructor to initialize the ToDo class.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the ToDo that will
     * be displayed to the user in the list.
     *
     * @return The string representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the task to be stored in a local file.
     *
     * @return The storage string representation of the task.
     */
    public String toStorageString() {
        return "T, " + isDone + ", " + description;
    }
}
