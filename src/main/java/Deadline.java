/**
 * This class is a subclass of Task which defines tasks with deadline.
 */
public class Deadline extends Task {
    /** A String that indicates deadline of task. */
    private String by;

    /**
     * A constructor for tasks with deadline
     * @param description Name of task
     * @param by Deadline of task to be completed by.
     * @throws HelpBuddyException If description or deadline is empty.
     */
    public Deadline(String description, String by) throws HelpBuddyException {
        super(description);
        this.by = by;
        if (description.isBlank()) {
            throw new HelpBuddyException("The description of a deadline cannot be empty.\n");
        } else if (by.isBlank()) {
            throw new HelpBuddyException("Please enter a deadline.\n");
        }
    }

    /**
     *
     * @return String representation of task with deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
