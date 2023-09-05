package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a <code>Deadline</code> object that extends from <code>Task</code>.
 * A <code>Deadline</code> object contains a deadline and a description.
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Constructs a new <code>Deadline</code> object.
     * @param description a description for the event.
     * @param deadline the deadline of the event.
     * @throws DukeException when the user fails to supply a deadline or description.
     */
    public Deadline(String description, String deadline) throws DukeException {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate d1 = LocalDate.parse(deadline, formatter);
            this.deadline = d1;
        } catch (Exception e) {
            throw new DukeException("Please provide a deadline in the format yyyy-MM-dd");
        }
    }

    /**
     * Gets the description of the <code>Deadline</code> object with the deadline in the format "MMM dd yyyy".
     * @return String representation of the <code>Deadline</code>> object to be displayed.
     */
    @Override
    public String getDescription() {
        String dueDate = this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D] " + super.getDescription()
                + " (by: " + dueDate + ")";
    }

    /**
     * Gets the description of the <code>Deadline</code> object.
     * @return String representation of the <code>Deadline</code> object to be saved to the task list file.
     */
    @Override
    public String getSavedString() {
        return "D " + super.getSavedString() + " | " + this.deadline;
    }
}
