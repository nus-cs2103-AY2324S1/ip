package friday;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Represents an deadline task in the Duke application.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate deadline;

    /**
     * Constructs a new Deadline task.
     *
     * @param description The description of the event.
     * @param by The deadline of the event.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    private String toDate() {
        //Solution below inspired by https://www.baeldung.com/java-datetimeformatter
        List<String> validPatterns = Arrays.asList("M/d/yyyy", "MM-dd-yyyy", "yyyy/MM/dd");
        for (String pattern : validPatterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                deadline = LocalDate.parse(by.trim(), formatter);
                return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Unsupported date format: " + by);
            }
         }
        throw new DateTimeParseException("Unsupported date format: " + by, by, 0);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + toDate() + ")";
    }
}