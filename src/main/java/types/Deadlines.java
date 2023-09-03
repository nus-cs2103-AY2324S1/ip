package types;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents the subset of Tasks that have to be done by a certain deadline.
 */
public class Deadlines extends Task{
    private final LocalDate by;

    /**
     * Initialises an instance of a Deadline.
     *
     * @param description description of the Task to be completed
     * @param by the deadline to complete the Task by in the format YYYY-MM-DD
     */
    public Deadlines(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Checks if the Deadline is due on the specified date.
     *
     * @param date the date to compare the Deadline's deadline to in the format YYYY-MM-DD
     * @return whether the Task is due on the given date or not
     */
    public boolean isToday(LocalDate date) {
        return (Objects.equals(this.by, date));
    }

    /**
     * Overrides the toString() method to return a customised String for each Deadline.
     *
     * @return String with "D" type and with a "[BY: xxx]" tag
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "[BY: " + this.by + "]";
    }


}
