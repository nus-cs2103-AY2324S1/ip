/**
 * The class representing a deadline task.
 *
 * @author Zhong Han
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructor for the deadline task.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description.strip());
        this.by = by.strip();
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return a string comprising the symbol, status,
     *      description and due date of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
