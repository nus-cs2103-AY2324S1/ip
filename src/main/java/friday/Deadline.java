package friday;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a deadline task in Friday.
 */
public class Deadline extends Task {
    protected String deadlineStr;
    protected LocalDate deadline;

    /**
     * Constructs a new Deadline task.
     *
     * @param description The description of the event.
     * @param deadlineStr The deadline of the event.
     */
    public Deadline(String description, String deadlineStr) {
        super(description);
        this.deadlineStr = deadlineStr;
    }

    /**
     * Formats the deadline string
     *
     * @return Formatted date string in the format "MMM d yyyy".
     * @throws DateTimeParseException if the provided deadline cannot be parsed.
     */
    private String toDate() {
        //Solution below inspired by https://www.baeldung.com/java-datetimeformatter
        List<String> validPatterns = Arrays.asList("M/d/yyyy", "MM-dd-yyyy", "yyyy/MM/dd");
        for (String pattern : validPatterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                deadline = LocalDate.parse(deadlineStr.trim(), formatter);
                return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Failed to parse date with unsupported pattern: " + pattern);
            }
        }
        throw new DateTimeParseException("Unsupported date format: " + deadlineStr, deadlineStr, 0);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + toDate() + ")";
    }
}
