package types;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents the subset of Tasks that are events and have a start and end.
 */
public class Party extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Initialises an instance of a Party.
     *
     * @param description description of the Party
     * @param from the start date of the Party
     * @param to the end date of the Party
     */
    public Party(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Checks if the Party is due on the specified date.
     *
     * @param date the date to compare the Party's deadline to in the format YYYY-MM-DD
     * @return whether the Party is held on the given date or not
     */
    public boolean isToday(LocalDate date) {
        return (Objects.equals(this.from, date));
    }

    /**
     * Overrides the toString() method to return a customised String for each Todo.
     *
     * @return String with "P" type and with the "~from:xx, to:xx~" tag.
     */
    @Override
    public String toString() {
        return "{P}" + super.toString() + "~from: " + this.from + ", to: " + this.to + "~";
    }
}
