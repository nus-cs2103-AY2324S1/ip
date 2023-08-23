/**
 * CS2103T Individual project
 * AY2023/24 Semester 1
 *
 * @author Anthony Tamzil
 */
public class ToDo extends Task {
    /**
     * A constructor to initialize the ToDo class.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the ToDo that will
     * be displayed to the user in the list.
     *
     * @return The string representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
