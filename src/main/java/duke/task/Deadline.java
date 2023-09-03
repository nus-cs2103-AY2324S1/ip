package duke.task;

import duke.time.Time;

import java.time.LocalDateTime;

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
     * constructor for duke.task.Event duke.task
     * @param description the text stored
     */
    public Deadline(String description, String by) {
        super(description);
        this.time = Time.toLocalDateTime(by);
    }

    /**
     * override the toString method
     * @return a string
     */
    @Override
    public String toString() {
        return "[" + this.TYPE + "]" + super.toString() +
                " (by: " + Time.toString(this.time) + ")";
    }

    @Override
    public String toDataString() {
        return this.TYPE + " / " + super.toDataString() + " / " + Time.toDataString(this.time);
    }
}
