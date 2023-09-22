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

    /**
     * Equals method to help with checking duplicates.
     *
     * @param obj obj compared with
     * @return if the object is equals or not.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;

            boolean equalDescription = this.description == deadline.description;
            boolean equalTime = this.time == deadline.time;
            if (equalDescription && equalTime) {
                return true;
            }

            if (this.description == null || deadline.description == null) {
                return false;
            }

            boolean strictEqualDescription = this.description.equals(deadline.description);
            boolean strictEqualTime = this.time.equals(deadline.time);
            return strictEqualDescription && strictEqualTime;
        }

        return false;
    }

    /**
     * Equals method to check if the text is equal.
     *
     * @param obj Object to compare with.
     * @return if the text equals or not.
     */
    @Override
    public boolean equalsText(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;

            return super.equals(deadline);
        }

        return false;
    }
}
