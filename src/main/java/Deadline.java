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

    public Deadline(String bool, String description, String by) {
        super(description.strip());
        this.by = by.strip();
        if (Integer.parseInt(bool) == 1) {
            this.isDone = true;
        }
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

    /**
     * Returns the string representing the task to be written in the disk.
     *
     * @return The string describing this task to be written in the disk.
     */
    @Override
    public String toStringForFile() {
        return "D | " + super.toStringForFile() + " | " + this.by;
    }
}
