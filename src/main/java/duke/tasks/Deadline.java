package duke.tasks;

import duke.exceptions.DukeInvalidArgumentException;

/**
 * Represents a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    /** The date/time of the deadline. */
    private String endTime;

    /**
     * Creates a new Deadline object.
     *
     * @param description The description of the deadline.
     * @param endTime     The date/time of the deadline.
     */
    public Deadline(String description, String endTime) throws DukeInvalidArgumentException {
        super(description);
        this.endTime = Task.parseDateInput(endTime);
    }

    /**
     * Returns the save format of the deadline, to be written to the save file.
     *
     * @return The save format of the deadline.
     */
    @Override
    public String getSaveFormat() {
        return String.format("DEADLINE||%d||%s /by %s\n", this.getIsDone() ? 1 : 0, this.getDescription(),
                this.endTime);
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Task.getDateOutputString(endTime));
    }
}
