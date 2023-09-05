package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class with 2 private fields, its deadline and symbol
 *
 * @author wj331
 */
public class Deadline extends Task {
    private static final String SYMBOL = "[D]";
    private String deadline;

    /**
     * Constructor for Deadline Task
     * @param name Name of the deadline
     * @param deadline Deadline of the deadline
     */
    public Deadline(String name, String deadline) {
        super(name);
        verifyDeadline(deadline);
    }
    /**
     * Parses the deadline if it is in correct format and ignores it otherwise
     * @param date Input date of the deadline
     */
    public void verifyDeadline(String date) {
        //pretty hard coded here, i think can be improved later eg if 1 d provided
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
            String formattedDate = dateTime.format(outputFormatter);
            this.deadline = formattedDate;
        } catch (DateTimeParseException e) {
            //not in expected format
            this.deadline = date;
        }
    }

    @Override
    public String newFormat() {
        return Deadline.SYMBOL + " | " + this.getInt() + " | " + this.getName() + " | " + this.deadline;
    }
    @Override
    public String toString() {
        return Deadline.SYMBOL + this.getCheckbox() + this.getName() + " (by: " + deadline + ")";
    }

    /**
     * Gets the deadline of deadline object
     * @return String value of the deadline
     */
    public String getDeadline() {
        return this.deadline;
    }
}
