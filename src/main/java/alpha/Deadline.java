package alpha;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class representation of a deadline which has an ending time.
 */
public class Deadline extends Task {

    private LocalDate dateBy;
    private LocalTime timeBy;

    private Deadline(String description, LocalDate dateBy) {
        super(description.trim());
        this.dateBy = dateBy;
    }

    private Deadline(String description, LocalDate dateBy, LocalTime timeBy) {
        super(description.trim());
        this.dateBy = dateBy;
        this.timeBy = timeBy;
    }

    public static Deadline makeDeadline(String description, String by) throws InvalidFormatException,
            DateTimeParseException{
            String trimmedDescription = description.trim();
            String trimmedBy = by.trim();
            if (trimmedDescription.length() == 0) {
                throw new InvalidFormatException("Missing a description.", TaskException.TaskType.DEADLINE);
            }
            String[] splitBy = trimmedBy.split(" ");
            if (splitBy.length == 2) {
                return new Deadline(trimmedDescription, LocalDate.parse(splitBy[0]),
                        LocalTime.parse(splitBy[1], DateTimeFormatter.ofPattern("HHmm")));
            } else {
                return new Deadline(trimmedDescription, LocalDate.parse(splitBy[0]));
            }
    }

    public String getDateBy() {
        return dateBy.toString();
    }

    public String getTimeBy() {
        if (timeBy != null) {
            return timeBy.format(DateTimeFormatter.ofPattern("HHmm"));
        }
        return "";
    }

    @Override
    public String toString() {
        if (timeBy != null) {
            return "[D]" + super.toString() + " (by: " + dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                    " " + timeBy.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
        }
        return "[D]" + super.toString() + " (by: " + dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
