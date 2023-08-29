import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines  extends Task{
    protected LocalDateTime by;

    /**
     * A constructor for the public class Deadlines.
     * @param description contains the description of the deadline.
     * @param by contains the time which the deadline must be completed by.
     */
    public Deadlines(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Please input your date and time in the correct format: yyyy-MM-dd HHmm");
        }
    }

    /**
     * This method converts the value of the deadline into a String type.
     * @return the String representation of the deadline with its type, completion status and completion time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
