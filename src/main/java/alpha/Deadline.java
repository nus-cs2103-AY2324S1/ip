package alpha;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalTime;

// Class representation of a deadline which has an ending time

public class Deadline extends Task{
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

    public static Deadline makeDeadline(String description, String by) {
        try {
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
        } catch (DateTimeParseException e1) {
            System.out.println("The date is in an invalid format! Enter the date in the format YYYY-MM-DD");
        } catch (InvalidFormatException e2) {
            System.out.println(e2.getMessage() + " Please enter a description before the timing of the deadline.");
        }
        return null;
    }


    public String getDateBy() {
        return this.dateBy.toString();
    }

    public String getTimeBy() {
        if (this.timeBy != null) {
            return this.timeBy.format(DateTimeFormatter.ofPattern("HHmm"));
        }
        return "";
    }

    @Override
    public String toString() {
        if (this.timeBy != null) {
            return "[D]" + super.toString() + " (by: " + this.dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                    " " + this.timeBy.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
        }
        return "[D]" + super.toString() + " (by: " + this.dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}