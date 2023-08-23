/**
 * CS2103T Individual project
 * AY2023/24 Semester 1
 *
 * @author Anthony Tamzil
 */
public class Deadline extends Task {
    /** A string indicating deadline of task */
    protected String by;

    /**
     * A constructor to initialize the Deadline class.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline that will
     * be displayed to the user in the list.
     *
     * @return The string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
