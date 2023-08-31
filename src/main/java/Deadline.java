import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    /**
     * the type Icon
     */
    private String type = "D";
    /**
     * the time the deadline must be finished
     */
    private LocalDateTime time;

    /**
     * constructor for Event task
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
        return "[" + this.type + "]" + super.toString() +
                " (by: " + Time.toString(this.time) + ")";
    }

    @Override
    public String toDataString() {
        return this.type + " / " + super.toDataString() + " / " + Time.toDataString(this.time);
    }
}
