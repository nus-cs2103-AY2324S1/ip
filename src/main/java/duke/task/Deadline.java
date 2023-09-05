package duke.task;

import java.time.LocalDateTime;

import duke.time.Time;


/**
 * A Class that handle deadline
 *
 * @author marioalvaro
 */
public class Deadline extends Task {
    /**
     * the type Icon
     */
    private static final String TYPE = "D";
    /**
     * the duke.time the deadline must be finished
     */
    private LocalDateTime time;

    /**
     * constructor for crating Deadline
     *
     * @param description the text stored
     * @param by the time the deadline must be finished
     */
    public Deadline(String description, String by) {
        super(description);
        this.time = Time.toLocalDateTime(by);
    }

    /**
     * override the toString method.
     *
     * @return a string
     */
    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString()
                + " (by: " + Time.toString(this.time) + ")";
    }

    /**
     * Method to create string that in match with the storing format.
     *
     * @return string that in match with the storing format.
     */
    @Override
    public String toDataString() {
        return TYPE + " / " + super.toDataString() + " / " + Time.toDataString(this.time);
    }
}
