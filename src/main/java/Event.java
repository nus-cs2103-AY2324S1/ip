import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, String from, String to) throws FishronException {
        super(description);
        System.out.println(description);
        System.out.println(from);
        System.out.println(to);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new FishronException("☹ OOPS!!! Please provide a valid date/time format. e.g. 21-05-2023 1200");
        }
    }

    public String toFileString() {
        return "E | " + super.toFileString() + " | " + from.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")) + " | " +
                to.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma")) + " to: " +
                to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma")) + ")";
    }
}