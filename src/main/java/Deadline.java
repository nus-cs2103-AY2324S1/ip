import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Deadline extends Task {

    protected String by;

    protected LocalDate deadlineDate;

    protected LocalDateTime deadLineDateAndTime;

    public Deadline(String description, String by) {
        super(description);
        this.deadlineDate = parseDate(by);
        this.deadLineDateAndTime = parseDateTime(by);
        this.by = by;
    }

    @Override
    public String toString() {
        if (deadlineDate != null) {
            String outputDate = deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            return "[D]" + super.toString() + "(by: " + outputDate + ")";
        } else if (deadLineDateAndTime != null) {
            String outputDate = deadLineDateAndTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            return "[D]" + super.toString() + "(by: " + outputDate + ")";
        } else {
            return "[D]" + super.toString() + "(by: " + by + ")";
        }
    }
    @Override
    public String writeToFile() {
        String printedStuff = "D | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | ";
        if (deadlineDate != null) {
            return printedStuff + this.deadlineDate;
        } else if (deadLineDateAndTime != null) {
            return printedStuff + this.deadLineDateAndTime;
        } else {
            return printedStuff + this.by;
        }
    }

    protected LocalDate parseDate(String date) {
        List<DateTimeFormatter> formats = new ArrayList<>();
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        formats.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        formats.add(DateTimeFormatter.ofPattern("d/M/yyyy"));

        for (int i = 0; i < formats.size(); i++) {
            try {
                return LocalDate.parse(date, formats.get(i));
            } catch (DateTimeParseException e) {

            }
        }
        return null;
    }

    protected LocalDateTime parseDateTime(String date) {
        List<DateTimeFormatter> formats = new ArrayList<>();
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        formats.add(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        formats.add(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));

            for (int i = 0; i < formats.size(); i++) {
                try {
                    return LocalDateTime.parse(date, formats.get(i));
                } catch (DateTimeParseException e) {

            }
        }

        return null;
    }
}
