package duke.task;

import duke.time.Time;

import java.time.LocalDateTime;

/**
 * A Class that handle deadline
 *
 * @author marioalvaro
 */
public class Deadline extends Task{
    /**
     * the type Icon
     */
    private final String TYPE = "D";
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
        return "[" + this.TYPE + "]" + super.toString() +
                " (by: " + Time.toString(this.time) + ")";
    }

    /**
     * Method to create string that in match with the storing format.
     *
     * @return string that in match with the storing format.
     */
    @Override
    public String toDataString() {
        return this.TYPE + " / " + super.toDataString() + " / " + Time.toDataString(this.time);
    }
}
